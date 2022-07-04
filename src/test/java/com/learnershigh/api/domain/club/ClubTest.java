package com.learnershigh.api.domain.club;

import static org.assertj.core.api.Assertions.assertThat;

import com.learnershigh.api.domain.club.dao.ChannelRepository;
import com.learnershigh.api.domain.club.dao.ClubChannelRepository;
import com.learnershigh.api.domain.club.dao.ClubMemberRepository;
import com.learnershigh.api.domain.club.dao.ClubRepository;
import com.learnershigh.api.domain.club.domain.Channel;
import com.learnershigh.api.domain.club.domain.Club;
import com.learnershigh.api.domain.club.domain.ClubChannel;
import com.learnershigh.api.domain.club.domain.ClubMember;
import com.learnershigh.api.domain.club.model.PermissionLevel;
import com.learnershigh.api.domain.member.dao.MemberRepository;
import com.learnershigh.api.domain.member.domain.Member;
import com.learnershigh.api.domain.model.Role;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClubTest {

	@Autowired MemberRepository memberRepository;
	@Autowired ChannelRepository channelRepository;
	@Autowired ClubRepository clubRepository;
	@Autowired ClubMemberRepository clubMemberRepository;
	@Autowired ClubChannelRepository clubChannelRepository;

	@Test
	public void 유저가_클럽을_생성한다(){

		// Given
		Member member = Member.builder()
			.username("club-maker-otter1")
			.password("sudal1234")
			.nickname("cmotr")
			.email("otter@dev.net")
			.verifiedEmail(false)
			.role(Role.MEMBER)
			.build();

		Club club = Club.builder()
			.clubName("TClub1")
			.password("club1234")
			.build();

		ClubMember clubMember = ClubMember.builder()
			.club(club)
			.member(member)
			.permissionLevel(PermissionLevel.MASTER)
			.banned(false)
			.build();

		memberRepository.save(member);
		clubRepository.save(club);
		clubMemberRepository.save(clubMember);


		// When
		ClubMember expectClubMember = clubMemberRepository.findByMember(member);


		// Then
		Assertions.assertThat(expectClubMember.getMember().getId())
			.isEqualTo(member.getId());

	}
	@Test
	public void 유저가_클럽에_가입한다(){

		// Given
		Member member = Member.builder()
			.username("otteer1")
			.password("sudal1234")
			.nickname("mynickname")
			.email("otter@dev.net")
			.verifiedEmail(false)
			.role(Role.MEMBER)
			.build();

		Club club = Club.builder()
			.clubName("Test-Club")
			.password("club1234")
			.build();

		ClubMember clubMember = ClubMember.builder()
			.club(club)
			.member(member)
			.permissionLevel(PermissionLevel.MASTER)
			.banned(false)
			.build();

		memberRepository.save(member);
		clubRepository.save(club);
		clubMemberRepository.save(clubMember);


		// When
		ClubMember willMemberJoinClub = clubMemberRepository.findById(clubMember.getId()).orElse(null);


		// Then
		if (willMemberJoinClub != null)
			assertThat(willMemberJoinClub.getMember().getId()).isEqualTo(member.getId());

	}
	@Test
	public void 유저에게_클럽_내_권한을_부여한다(){

		// Given
		Member master = Member.builder()
			.username("master")
			.password("master1234")
			.nickname("master")
			.email("cm@dev.net")
			.verifiedEmail(false)
			.role(Role.MEMBER)
			.build();

		Club club = Club.builder()
			.clubName("Deli-Perm-Test-Club")
			.password("club1234")
			.build();

		ClubMember clubMember = ClubMember.builder()
			.club(club)
			.member(master)
			.permissionLevel(PermissionLevel.MASTER)
			.banned(false)
			.build();

		memberRepository.save(master);
		clubRepository.save(club);
		clubMemberRepository.save(clubMember);

		Member operator = Member.builder()
			.username("be-operator")
			.password("iwannabeops1234")
			.nickname("mr.ops")
			.email("ops@ops.net")
			.verifiedEmail(false)
			.role(Role.MEMBER)
			.build();

		ClubMember permNeededMember = ClubMember.builder()
			.club(club)
			.member(operator)
			.permissionLevel(PermissionLevel.OPERATOR)
			.banned(false)
			.build();

		memberRepository.save(operator);
		clubMemberRepository.save(permNeededMember);


		// When
		ClubMember isPermElavatedUser = clubMemberRepository.findAllByClub(club).get(1);


		// Then
		assertThat(isPermElavatedUser.getPermissionLevel()).isEqualTo(PermissionLevel.OPERATOR);
	}

	@Test
	public void 클럽에_새로운_서브채널을_생성한다(){

		// Given
		Club club = Club.builder()
			.clubName("testclub")
			.visible(true)
			.build();

		Channel channel = Channel.createDefaultChannel();

		ClubChannel clubChannel = ClubChannel.builder()
			.club(club)
			.channel(channel)
			.build();

		clubRepository.save(club);
		channelRepository.save(channel);
		clubChannelRepository.save(clubChannel);


		// When
		Channel willDefaultChannel = clubChannelRepository.findAllByClub(club).get(0).getChannel();

		
		// Then
		Assertions.assertThat(willDefaultChannel.getName()).isEqualTo("Welcome");
		Assertions.assertThat(willDefaultChannel.getPermissionLevel()).isEqualTo(PermissionLevel.MEMBER);
	}
}
