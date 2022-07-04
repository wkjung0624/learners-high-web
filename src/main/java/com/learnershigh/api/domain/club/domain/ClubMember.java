package com.learnershigh.api.domain.club.domain;

import com.learnershigh.api.domain.club.model.PermissionLevel;
import com.learnershigh.api.domain.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

	// 최초 생성자 ID 또는 마스터 권한 위임자(Member 클래스로 매핑 필요)
	@Column(nullable = false)
	private PermissionLevel permissionLevel;

	// 해당 클럽에서 퇴장당했을 때 재입장 제한을 위한 값
	@Column(nullable = false)
	private boolean banned;
}