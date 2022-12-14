package com.learnershigh.api.domain.member.api;

import com.learnershigh.api.global.common.response.StandardResponseBody;
import com.learnershigh.api.global.common.response.data.DataLayer;
import java.util.Map;
import java.util.HashMap;
import com.learnershigh.api.domain.member.application.MemberService;
import com.learnershigh.api.domain.member.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserInformationApi {
	// User 파트에서 'Read' 와 'Update' 를 담당
	private final MemberService memberService;

	/** [v] 사용자 로그인 */
	@PostMapping("/user")
	public ResponseEntity<StandardResponseBody> login(@RequestBody @Valid Map<String, String> requestBody, HttpServletRequest request) {
		// x 최근(3분)에 아이디를 가져왔다면 Redis 캐쉬에 해당 정보를 저장하고
		// x 비밀번호는 Redis 캐쉬 데이터에서 찾는것이 저비용으로 로그인이 가능한 방법일듯
		// ㅇ 조건1. 로그아웃인 상태부터 확인
		// ㅇ 조건2. 조건 1 선행 후 입력한 ID와 PW가 DB의 값과 일치하는지 확인

		HttpSession session = request.getSession();
		Map<String, String> result = new HashMap<>();

		if (memberService.hasAuthenticateInformation(session)) {
			result.put("result","false");
			result.put("msg","logout first");
			return null;
			// return result; >> throw exception logout first 처리,(그렇지만 로그아웃이 필요할까? 자동적으로 세션아웃 시켜주는건?)
		}
		else {
			Member member = memberService.getUser(requestBody.get("username"), requestBody.get("password"));

			if(member == null) {
				result.put("result","false");
				result.put("msg","no matching");

				// return result; >> throw exception nomatch 처리
			}

			session.setAttribute("username", member.getUsername());
			session.setAttribute("password", member.getPassword());
			session.setAttribute("role", member.getRole());
			session.setAttribute("nickname", member.getNickname());
			session.setAttribute("email", member.getEmail());
			session.setMaxInactiveInterval(12*60*60); // 자동 로그아웃 시간 60초

			//session.getLastAccessedTime(); // 마지막으로 접근한 시간, 브라우저 종료 후에도 세션이 살아있는걸 방지

			result.put("result", "ok");
			result.put("msg", "hit");

			return ResponseEntity
				.status(HttpStatus.OK)
				.body(StandardResponseBody.builder()
					.data(DataLayer.builder()
						.status(true)
						.build())
					.build());
		}
	}
	@GetMapping("/user/logout")
	public ResponseEntity<StandardResponseBody> logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);

		if(request.getSession() != null){
			session.invalidate();
		}

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(StandardResponseBody.builder()
				.data(DataLayer.builder()
					.status(true)
					.build())
				.build());
	}

	/** [v] 회원 정보 수정 (이메일, 이름, 닉네임)
	 * 받을 정보
	 * Header : JWT
	 * Body : email, name, nickname
	 * */
	@PostMapping("/user/info/update")
	public ResponseEntity<StandardResponseBody> updateUserInformation(@RequestBody @Valid Map<String, String> requestBody) {

		Member member = memberService.findOneByUsername(requestBody.get("username"));

		Map<String, String> result = new HashMap<>();

		if(member != null) {
			if(requestBody.get("email") != null) {
				member.setEmail(requestBody.get("email"));
				member.setVerifiedEmail(false);

				result.put("isEmailChanged", "true");
			}
			if(requestBody.get("nickname") != null) {
				member.setNickname(requestBody.get("nickname"));
				result.put("isNicknameChanged", "true");
			}

			memberService.save(member);

			return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(StandardResponseBody.builder()
					.data(DataLayer.builder()
						.status(true)
						.build())
					.build());
		}

		result.put("result","false");
		result.put("msg","no match");
		return null;
	}

	/** 사용자 계정 찾기
	 * 계정 찾기 이름과 주민번호를 이용하는건 X (고유식별정보 처리 근거가 부족함) 메일에 확인 클릭 시 비밀번호 초기화가 가능함 비밀번호를 직접적으로 알려주는건 X
	 */
	@PostMapping("/user/id/find")
	public ResponseEntity<StandardResponseBody> findUserAccount(@RequestParam String email) {

		Map<String, Object> result = new HashMap<>();
		Member member = memberService.findOneByEmail(email);

		if (member != null){
			result.put("result", "SUCCESS");
			result.put("data", member.getUsername());
		} else {
			result.put("result", "FAIL");
			result.put("ERR_CODE", "DEV-ERO-1");
		}
		// @RequestParam 말고 @RequestBody 로 변경하기;
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(StandardResponseBody.builder()
				.data(DataLayer.builder()
					.status(true)
					.build())
				.build());

		// TODO: 해당 이메일로 아이디 정보와 비밀번호 초기화 링크를 보내는 기능 추가 필요
		// TODO: 최상위 ResponseDto 클래스를 만들고 그 안에 제네릭클래스를 만들어서 사용하는건? 성공시 정상 DTO 매핑, 실패시 Excepion DTO 매핑
		// TODO: 알고보니 ResponseEntity 라는 기본 클래스가 있네?
	}

	/**
	 * '계정 찾기'에서 발급받은 키로 로그인 없이 비밀번호 변경하기
	 * 1. Http 302 코드 (임시 리다이렉트) 사용
	 * 2. 해당 키를 사용한 페이지는 만료되도록 처리
	 */
	/* @GetMapping("/user/password/{key}/reset") - 이건 인덱스컨트롤러에서 해야할 작업일 듯 (변경페이지 전환)
		키값이 Redis 와 불일치 시
		response.put("data", "/redirect-error-page");
		// "만료된 키 혹은 폐기된 키값 입니다."  출력
		// "에러페이지로 이동할 링크로 리다이렉트"
	 */
	@PostMapping("/user/password/{key}/reset/send")
	public ResponseEntity<StandardResponseBody> resetUserPassword(@PathVariable String key, @RequestBody Map<String, String> requestBody) {
		Map<String, Object> result = new HashMap<>();

		// TODO : 해당 API 주소에 무작위 조회를 막기위해 Limit 을 사용함
		// TODO : Redis 에서 Reset key 발급 확인
		/* key 가 Redis 에 저장된 형태
		Type : HashMap
		Key : "spring:password:resetkey:<UUID 형태의 고유값>
			userId : <비밀번호를 변경할 유저의 아이디>
			expireDate : <발급일로부터 3분>
			referer : <리셋 메일을 보낸 대상의 이메일 도메인, 예) *.naver.com>

		예>> spring:password:resetkey:29919a9a8ab-920o21-12999982350129
		 */

		if (true) { // "Redis 에 해당 키 발견시!"
			String username = "skyship36"; // redis 에 저장된 userId 값, 유저에게 노출되어선 안됨. 악의적인 사용자가 우연히 접속한 경우도 고려해야함

			result.put("key", key);
			result.put("result", "success");
			result.put("data", "changed passwd:" + requestBody.get("password"));
			memberService.changePassword(username, requestBody.get("password"));

			return ResponseEntity
				.status(HttpStatus.OK)
				.body(StandardResponseBody.builder()
					.data(DataLayer.builder()
						.status(true)
						.build())
					.build());
		}

		return null;
	}

	/**
	 * 로그인 한 상태에서 비밀번호 변경하기 현재 비밀번호 입력 후 새로운 비밀번호를 입력받아야함
	 * Header : JWT
	 * Body : '현재 비밀번호', '바꿀 비밀번호', '바꿀 비밀번호 확인'
	 */
	// 인덱스 "/user/password/update/send"
	@PostMapping("/user/password/update/send")
	public ResponseEntity<StandardResponseBody> updateUserPassword(@RequestBody Map<String, String> requestBody){

		Member member = memberService.getUser(requestBody.get("username"), requestBody.get("password"));

		Map<String, String> result = new HashMap<>();

		if(member != null){
			member.setPassword(requestBody.get("changePassword"));

			result.put("result", "true");
			return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(StandardResponseBody.builder()
					.data(DataLayer.builder()
						.status(true)
						.build())
					.build());
		}
		result.put("result", "false");
		return null;
	}
}
