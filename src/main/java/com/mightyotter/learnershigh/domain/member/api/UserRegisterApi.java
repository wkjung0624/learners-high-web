package com.mightyotter.learnershigh.domain.member.api;

import com.mightyotter.learnershigh.domain.member.application.MemberService;
import com.mightyotter.learnershigh.domain.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// private final 을 사용하기 위해 @RequiredArgsConstructor 를 쓰는 이유는?
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserRegisterApi {

	// private final 인 이유는?
	private final MemberService memberService;

	/** [√] 유저가 입력한 정보로 임시회원가입 */
	@PostMapping("/register")
	public Long createUserAccount(@RequestBody(required = true) MemberSaveRequestDto memberSaveRequestDto){
		return memberService.save(memberSaveRequestDto);
	}

	/** 회원 가입 전 이메일 중복 확인
	 * 테스트 데이터
	 * c2t5c2hpcDM2QGdtYWlsLmNvbQ==
	 * skyship36@gmail.com
	 // TODO : 이메일 형식인지 정규표현식 검사
	 // TODO : 이메일이 존재한다면 OK Json
	 // TODO : 이메일이 존재하지 않다면 Error Json
	 * @param address
	 * @return
	 */
	@GetMapping("/register/email/duplicated/")
	public String checkDuplicatedEmail(@RequestParam(required = true) String address) {
		return memberService.findByEmail(address);
	}

	/** 회원 가입한 유저에게 이메일 인증 요청
	 // TODO : 해당 이메일로 일회용 인증키 값 보내기
	 // TODO : 일회용 인증키 값은 시간을 Seed 로 랜덤값
	 // TODO : 1시간 내에 인증 완료 시 OK, 미완료시 임시 가입 정보 폐기
	 * Base64Utils.decodeFromString(address);
	 * @param address
	 * @return
	 */
	@GetMapping("/register/email/challange") // base64encoded email
	public Object sendEmailChallenge(@RequestParam(required = true) String address) {
		return null;
	}

	/** 이메일 인증 링크 클릭시 검증하는 메소드
	 * 메일 인증만 예외적으로 GET 을 허용
	 * HTTP Header 의 Referer 와 인증한 메일의 도메인이 동일한지 확인 (조작 가능하므로 추가 조치 필요)
	 * @param key
	 * @return
	 */
	@GetMapping("/register/email/challange-accept/") // /dup../?address=
	public String receiveEmailChallangeAccept(@RequestParam(required = true) String key) {
		return "this is mock ";
	}

	/** SMS 인증 API, 현재는 e-mail 인증 기능 개발 우선
		//휴대폰 인증 요청
		@PostMapping("/register/phone/challange")
		public String sendPhoneChallange() { return null; }

		//휴대폰 인증 응답
		@PostMapping("/register/phone/challange-accept")
		public String receivePhoneChallange() { return null; }
	 */
}