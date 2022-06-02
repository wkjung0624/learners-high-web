package com.mightyotter.learnershigh.domain.member.api;

import com.mightyotter.learnershigh.domain.member.application.MemberService;
import com.mightyotter.learnershigh.domain.member.dto.MemberDeleteRequestDto;
import com.mightyotter.learnershigh.global.config.Role;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// private final 을 사용하기 위해 @RequiredArgsConstructor 를 쓰는 이유는?
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserRegisterApi {
	// User 파트에서 'Create' 와 'Delete' 를 담당
	// private final 인 이유는?
	private final MemberService memberService;

	/** [√] 유저가 입력한 정보로 임시 회원가입 */
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> createUserAccount(@RequestBody Map<String, String> requestBody){
		return ResponseEntity.ok().body(
			memberService.save(requestBody.get("userId"),requestBody.get("userPw"),requestBody.get("nickName"),requestBody.get("email"), Role.MEMBER));
	}

	/** [√] 사용자 계정 삭제하기
	 * 1. 30일 간 보관 후 삭제
	 * 2. 가입 시 입력했던 이메일로 삭제 조치 안내 메일 전송
	 * 3. 30일 이후 배치 스크립트가 자동으로 '삭제된 계정 DB' 로 백업 후 '원본 DB'에서 삭제
	 */
	@PostMapping("/register/delete")
	public ResponseEntity<Boolean> deleteUserAccount(@RequestBody MemberDeleteRequestDto memberDeleteRequestDto) {
		return ResponseEntity.ok().body(memberService.delete(memberDeleteRequestDto));
	}

	/** [√] 회원 가입 전 아이디 중복 확인 */
	@GetMapping("/register/id/duplicate")
	public boolean checkIdDuplication(@RequestParam(required = true) String userId) {
		return memberService.findOneByUserId(userId) != null;
	}

	/** [√] 회원 가입 전 이메일 중복 확인
	 * 테스트 데이터
	 * c2t5c2hpcDM2QGdtYWlsLmNvbQ==
	 * skyship36@gmail.com
	 // TODO : 이메일 형식인지 정규표현식 검사
	 // TODO : 이메일이 존재한다면 OK Json
	 // TODO : 이메일이 존재하지 않다면 Error Json
	 * @param email
	 * @return
	 */
	@GetMapping("/register/email/duplicate")
	public boolean checkEmailDuplication(@RequestParam(required = true) String email) {
		return memberService.findOneByEmail(email) != null;
	}

	/** [x] 회원 가입한 유저에게 이메일 인증 요청
	 // TODO : 해당 이메일로 일회용 인증키 값 보내기
	 // TODO : 일회용 인증키 값은 시간을 Seed 로 랜덤값
	 // TODO : 1시간 내에 인증 완료 시 OK, 미완료시 임시 가입 정보 폐기
	 * Base64Utils.decodeFromString(address);
	 * @param address
	 * @return
	 */
	@GetMapping("/register/email/challange") // base64encoded email
	public void sendEmailChallenge(@RequestParam(required = true) String address) {/*...*/}

	/** 이메일 인증 링크 클릭시 검증하는 메소드
	 * 메일 인증만 예외적으로 GET 을 허용
	 * HTTP Header 의 Referer 와 인증한 메일의 도메인이 동일한지 확인 (조작 가능하므로 추가 조치 필요)
	 * @param key
	 * @return
	 */
	@GetMapping("/register/email/challange-accept/") // /dup../?address=
	public void receiveEmailChallangeAccept(@RequestParam(required = true) String key) {/*...*/}

	/** SMS 인증 API, 현재는 e-mail 인증 기능 개발 우선
		//휴대폰 인증 요청
		@PostMapping("/register/phone/challange")
		public String sendPhoneChallange() { return null; }

		//휴대폰 인증 응답
		@PostMapping("/register/phone/challange-accept")
		public String receivePhoneChallange() { return null; }
	 */
}