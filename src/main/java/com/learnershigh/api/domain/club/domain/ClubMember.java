package com.learnershigh.api.domain.club.domain;

import com.learnershigh.api.domain.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
public class ClubMember {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLUB_MEMBER_ID")
	private Long id;

	// 클럽 아이디
	@ManyToOne
	//@JoinColumn(name = "CLUB_ID")
	private Club club;

	// 유저 아이디
	@ManyToOne
	//@JoinColumn(name = "MEMBER_ID")
	private Member member;

	// 해당 클럽에서 퇴장당했을 때 재입장 제한을 위한 값
	@Column(nullable = false)
	private boolean banned;
}

//	@Column(nullable = false)
//	private String authority;


	// 클럽 고유번호
	// 클럽명
	// 클럽 관리자(생성자)
	// 클럽 매니저
	// 클럽 유저

