package com.mightyotter.learnershigh.domain.member.application;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import com.mightyotter.learnershigh.domain.member.dao.MemberRepository;
import com.mightyotter.learnershigh.domain.member.dto.MemberCreateRequestDto;

import com.mightyotter.learnershigh.domain.member.dto.MemberLoginRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public Long save(MemberCreateRequestDto memberSaveRequestDto){
		return memberRepository.save(memberSaveRequestDto.toEntity()).getId();
	}

	public List<Member> findByUserIdAndUserPw(MemberLoginRequestDto memberLoginRequestDto) {
		return memberRepository.findByUserIdAndUserPw(memberLoginRequestDto.getUserId(),
			memberLoginRequestDto.getUserPw());
	}
	public List<Member> findByEmail(String email){
		return memberRepository.findByEmail(email);
	}
	public List<Member> findByUserId(String userId){
		return memberRepository.findByUserId(userId);
	}
}
