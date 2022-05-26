package com.mightyotter.learnershigh.domain.member.api;

import com.mightyotter.learnershigh.domain.member.application.MemberService;
import com.mightyotter.learnershigh.domain.member.dao.Member;
import com.mightyotter.learnershigh.domain.member.dto.MemberLoginRequestDto;
import com.mightyotter.learnershigh.domain.member.dto.MemberLoginResponseDto;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	private final MemberService memberService;

	/** 사용자 로그인 */
	@PostMapping("/user")
	public List<Member> getUserAccount(@RequestBody @Valid MemberLoginRequestDto memberLoginRequestDto){
		return memberService.findByUserIdAndUserPw(memberLoginRequestDto);
	}

	/** 사용자 계정 찾기
	 * 계정 찾기 이름과 주민번호를 이용하는건 X (고유식별정보 처리 근거가 부족함) 메일에 확인 클릭 시 비밀번호 초기화가 가능함 비밀번호를 직접적으로 알려주는건 X
	 */
	@PostMapping("/user/id/find")
	public String findUserAccount(@RequestParam String email) {

		List<Member> memberList = memberService.findByEmail(email);

		if (!memberList.isEmpty()) {
			String userEmail = memberList.get(0).getEmail();
			// TODO: 해당 이메일로 아이디 정보와 비밀번호 초기화 링크를 보내는 기능 추가 필요
			return "Check Password Reset Page in your email : " + userEmail;
		}

		return "nothing";
	}

	/** 사용자 계정 삭제하기
	 * 1. 30일 간 보관 후 삭제
	 * 2. 가입 시 입력했던 이메일로 삭제 조치 안내 메일 전송
	 * 3. 30일 이후 배치 스크립트가 자동으로 '삭제된 계정 DB' 로 백업 후 '원본 DB'에서 삭제
	 */
	@PostMapping("/user/info/delete")
	public void deleteUserAccount(@RequestBody String userid) {/*...*/}

	/**
	 * '계정 찾기'에서 발급받은 키로 로그인 없이 비밀번호 변경하기
	 * 1. Http 302 코드 (임시 리다이렉트) 사용
	 * 2. 해당 키를 사용한 페이지는 만료되도록 처리
	 */
	@GetMapping("/user/password/{key}/reset")
	public void resetUserPassword() {/*...*/}

	/**
	 * 로그인 한 상태에서 비밀번호 변경하기 현재 비밀번호 입력 후 새로운 비밀번호를 입력받아야함
	 * Header : JWT
	 * Body : '현재 비밀번호', '바꿀 비밀번호', '바꿀 비밀번호 확인'
	 */
	@PostMapping("/user/password/{key}/update")
	public void updateUserPassword() {/*...*/}

	/** 회원 정보 수정 (이메일, 이름, 닉네임)
	 * 받을 정보
	 * Header : JWT
	 * Body : email, name, nickname
	 * */
	@PostMapping("/user/info/update")
	public void updateUserInformation() {/*...*/}
}
