package com.mightyotter.learnershigh.domain.member.api;

import java.util.Map;
import java.util.HashMap;
import com.mightyotter.learnershigh.domain.member.application.MemberService;
import com.mightyotter.learnershigh.domain.member.dao.Member;
import com.mightyotter.learnershigh.domain.member.dto.request.MemberCreateRequestDto;
import com.mightyotter.learnershigh.domain.member.dto.request.MemberLoginRequestDto;
import com.mightyotter.learnershigh.domain.member.dto.request.MemberUpdateRequestDto;
import com.mightyotter.learnershigh.domain.member.dto.response.MemberLoginResponseDto;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	/** [x] 사용자 로그인 */
	@PostMapping("/user")
	public MemberLoginResponseDto getUserAccount(@RequestBody @Valid MemberLoginRequestDto memberLoginRequestDto){
		// TODO : Spring Session ID 를 참고해야함
		Member member = memberService.findOneByUserIdAndUserPw(memberLoginRequestDto.getUserId(),
			memberLoginRequestDto.getUserPw());

		if (member != null) {
			return new MemberLoginResponseDto("uuid-session", member.getUserId(), member.getNickName(), member.getEmail());
		}

		return null;
	}

	/** [v] 회원 정보 수정 (이메일, 이름, 닉네임)
	 * 받을 정보
	 * Header : JWT
	 * Body : email, name, nickname
	 * */
	@PostMapping("/user/info/update")
	public Map<String, Boolean> updateUserInformation(@RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto) {

		// System.out.println(memberUpdateRequestDto.getUserId() + " " + memberUpdateRequestDto.getEmail() + " " + memberUpdateRequestDto.getNickName());
		// TODO : 중복 이메일, 중복 닉네임 검사하기
		Member member = memberService.findOneByUserId(memberUpdateRequestDto.getUserId());

		Map<String, Boolean> result = new HashMap<>();

		if(member != null) {
			if(memberUpdateRequestDto.getEmail() != null) {
				member.setEmail(memberUpdateRequestDto.getEmail());
				member.setVerifiedEmail(false);

				result.put("isEmailChanged", true);
			}
			if(memberUpdateRequestDto.getNickName() != null) {
				member.setNickName(memberUpdateRequestDto.getNickName());
				result.put("isNicknameChanged", true);
			}

			memberService.save(MemberCreateRequestDto.builder()
				.userId(member.getUserId())
				.userPw(member.getUserPw())
				.nickName(member.getNickName())
				.email(member.getEmail())
				.build());
		}
		return result;
	}

	/** 사용자 계정 찾기
	 * 계정 찾기 이름과 주민번호를 이용하는건 X (고유식별정보 처리 근거가 부족함) 메일에 확인 클릭 시 비밀번호 초기화가 가능함 비밀번호를 직접적으로 알려주는건 X
	 */
	@PostMapping("/user/id/find")
	public Map<String, Object> findUserAccount(@RequestParam String email) {

		Map<String, Object> response = new HashMap<>();
		Member member = memberService.findOneByEmail(email);

		if (member != null){
			response.put("result", "SUCCESS");
			response.put("data", member.getUserId());
		} else {
			response.put("result", "FAIL");
			response.put("ERR_CODE", "DEV-ERO-1");
		}
		// @RequestParam 말고 @RequestBody 로 변경하기;
		return response;

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
	public Map<String, Object> resetUserPassword(@PathVariable String key, @RequestBody Map<String, String> requestBody) {
		Map<String, Object> response = new HashMap<>();

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
			String userId = "skyship36"; // redis 에 저장된 userId 값, 유저에게 노출되어선 안됨. 악의적인 사용자가 우연히 접속한 경우도 고려해야함

			response.put("result", "success");
			response.put("key", key);
			response.put("data", "changed passwd:" + requestBody.get("userPw"));
			memberService.changePassword(userId, requestBody.get("userPw"));

			return response;
		}

		return response;
	}

	/**
	 * 로그인 한 상태에서 비밀번호 변경하기 현재 비밀번호 입력 후 새로운 비밀번호를 입력받아야함
	 * Header : JWT
	 * Body : '현재 비밀번호', '바꿀 비밀번호', '바꿀 비밀번호 확인'
	 */
	// 인덱스 "/user/password/update/send"
	@PostMapping("/user/password/update/send")
	public boolean updateUserPassword(@RequestBody Map<String, String> requestBody){

		Member member = memberService.findOneByUserIdAndUserPw(requestBody.get("userId"), requestBody.get("userPw"));

		if(member != null){
			member.setUserPw(requestBody.get("changePw"));
			memberService.save(MemberCreateRequestDto.builder()
				.userId(member.getUserId())
				.userPw(member.getUserPw())
				.nickName(member.getNickName())
				.email(member.getEmail())
				.build());
			return true;
		}
		return false;
	}
}
