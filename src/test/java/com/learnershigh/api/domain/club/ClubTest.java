package com.learnershigh.api.domain.club;

import static org.assertj.core.api.Assertions.assertThat;

import com.learnershigh.api.domain.club.dao.ClubMemberRepository;
import com.learnershigh.api.domain.club.dao.ClubRepository;
import com.learnershigh.api.domain.club.domain.Club;
import com.learnershigh.api.domain.club.domain.ClubMember;
import com.learnershigh.api.domain.member.dao.MemberRepository;
import com.learnershigh.api.domain.member.domain.Member;
import com.learnershigh.api.domain.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClubTest {

	@Autowired
	ClubRepository clubRepository;
	@Autowired
	ClubMemberRepository clubMemberRepository;
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 유저가_클럽에_가입한다(){

		// given
		Member member = Member.builder()
			.username("otteer1")
			.password("sudal1234")
			.nickname("mynickname")
			.email("otter@dev.net")
			.verifiedEmail(false)
			.role(Role.MEMBER)
			.build();

		Club club = Club.builder()
			.master(member)
			.clubName("Test-Club")
			.password("club1234")
			.build();

		ClubMember clubMember = ClubMember.builder()
			.club(club)
			.member(member)
			.banned(false)
			.build();

		// when
		memberRepository.save(member);
		clubRepository.save(club);
		clubMemberRepository.save(clubMember);

		// then
		ClubMember clubMember1 = clubMemberRepository.findById(clubMember.getId()).get();

		assertThat(clubMember1.getMember().getId()).isEqualTo(member.getId());

	}
}
