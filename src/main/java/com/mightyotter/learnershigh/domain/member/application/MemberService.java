package com.mightyotter.learnershigh.domain.member.application;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import com.mightyotter.learnershigh.domain.member.dao.MemberRepository;
import com.mightyotter.learnershigh.domain.member.dto.MemberSaveRequestDto;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public Long save(MemberSaveRequestDto memberSaveRequestDto){
		return memberRepository.save(memberSaveRequestDto.toEntity()).getId();
	}

	public List<Member> findByEmail(String email){
		return memberRepository.findByEmail(email);
	}

}
