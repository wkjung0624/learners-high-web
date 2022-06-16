package com.mightyotter.learnershigh.domain.member.application;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import com.mightyotter.learnershigh.domain.member.dao.MemberRepository;
import com.mightyotter.learnershigh.domain.member.dto.MemberDeleteRequestDto;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service//("userDetailService")
@RequiredArgsConstructor
public class MemberService{
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	// 현재 세션이 Timeout 되지 않은 유효한 세션인가? (30분 : 30 * 60[초])
	public boolean isAuthenticationValidation(HttpSession userSession){
		Date currentTime = new Date();
		return currentTime.getTime() - userSession.getLastAccessedTime() <= (long) 30 * 60;
	}
	// 로그인 했는지 확인하는 Req 헤더 검증 메서드 만들어야함
	// 공통의 모듈로 따로 뺴야할까? (Common package)
	public boolean hasAuthenticateInformation(HttpSession userSession){
		return userSession.getAttribute("username") != null;
	}

	@Transactional(rollbackFor = Exception.class)
	public Member save(Member member){

		if(findOneByUsername(member.getUsername()) == null &&
			findOneByEmail(member.getPassword()) == null){
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			return memberRepository.save(member);
		}

		return null;
	}
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(MemberDeleteRequestDto memberDeleteRequestDto){

		Member member = getUser(memberDeleteRequestDto.getUsername(),
			memberDeleteRequestDto.getPassword());

		if (member != null){
			memberRepository.delete(member);
			return true;
		}
		// TODO: 아이디와 비밀번호 외에 유저의 "로그인 인증값(세션ID)" 도 같이 제출되어야함
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean changePassword(String username, String password){

		Optional<Member> memberFindResult = memberRepository.findOneByUsername(username);

		if(memberFindResult.isPresent()){
			Member member = memberFindResult.get();
			member.setPassword(passwordEncoder.encode(password));
			memberRepository.save(member);
			return true;
		}

		return false;
	}

	public Member findOneByEmail(String email){
		return memberRepository
			.findOneByEmail(email)
			.orElse(null);
	}
	public Member findOneByUsername(String username) {
		return memberRepository
			.findOneByUsername(username)
			.orElse(null);
	}

	public Member getUser(String username, String password) {

		Member member;

		if(false){ // if (rediscasch.get("userId") != null) {
			/* Redis 에서 기존에 저장한 findOneByUserId 메서드의 결과값을 가져와 선비교 후 DB 질의하기
			* member = RedisCache.get("userId");
			* */
		} else {
			// 레디스에 멤버 캐쉬 데이터 저장 과정 추가
			member = findOneByUsername(username);
		}

		if(member != null && passwordEncoder.matches(password, member.getPassword())) {
			return member;
		}

		return null;
	}
}
