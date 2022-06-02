package com.mightyotter.learnershigh.domain.member.application;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import com.mightyotter.learnershigh.domain.member.dao.MemberRepository;

import com.mightyotter.learnershigh.domain.member.dto.MemberDeleteRequestDto;
import com.mightyotter.learnershigh.global.config.Role;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// 현재 세션이 Timeout 되지 않은 유효한 세션인가? (30분 : 30 * 60[초])
	public boolean isAuthenticationValidation(HttpSession userSession){
		Date currentTime = new Date();
		return currentTime.getTime() - userSession.getLastAccessedTime() <= (long) 30 * 60;
	}
	// 로그인 했는지 확인하는 Req 헤더 검증 메서드 만들어야함
	// 공통의 모듈로 따로 뺴야할까? (Common package)
	public boolean hasAuthenticateInformation(HttpSession userSession){
		return userSession.getAttribute("userId") != null;
	}

	@Transactional(rollbackFor = Exception.class)
	public Map<String, String> save(String userId, String userPw, String nickName, String email, Role role){

		Map<String, String> response = new HashMap<>();

		if(findOneByUserId(userId) == null &&
			findOneByEmail(userPw) == null){

			memberRepository.save(Member.builder()
				.userId(userId)
				.userPw(userPw) // 서버 측에서 유저의 패스워드를 암호화하여 저장
				.nickName(nickName)
				.email(email)
				.verifiedEmail(false)
				.role(role)
				.build());

			response.put("result","ok");

			return response;
		}

		response.put("result","false");
		return response;
	}
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(MemberDeleteRequestDto memberDeleteRequestDto){

		Member member = getUser(memberDeleteRequestDto.getUserId(),
			memberDeleteRequestDto.getUserPw());

		if (member != null){
			memberRepository.delete(member);
			return true;
		}
		// TODO: 아이디와 비밀번호 외에 유저의 "로그인 인증값" 도 같이 제출되어야함
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean changePassword(String userId, String userPw){

		Optional<Member> member = memberRepository.findOneByUserId(userId);

		if(member.isPresent()){
			member.get().setUserPw(userPw);
			return true;
		}
		return false;
	}

	public Member findOneByEmail(String email){
		return memberRepository
			.findOneByEmail(email)
			.orElse(null);
	}
	public Member findOneByUserId(String userId) {
		return memberRepository
			.findOneByUserId(userId)
			.orElse(null);
	}

	public Member getUser(String userId, String userPw) {

		Member member;

		if(false){ // if (rediscasch.get("userId") != null) {
			/* Redis 에서 기존에 저장한 findOneByUserId 메서드의 결과값을 가져와 선비교 후 DB 질의하기
			* member = RedisCache.get("userId");
			* */
		} else {
			// 레디스에 멤버 캐쉬 데이터 저장 과정 추가
			member = findOneByUserId(userId);
		}

		if(member != null && bCryptPasswordEncoder.matches(userPw, member.getUserPw())) {
			return member;
		}

		return null;
	}
}
