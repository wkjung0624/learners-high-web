package com.mightyotter.learnershigh.domain.club.dao;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClubMember {

	@Id // @Column(name = "CLUB_MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 클럽 아이디
	@Column(nullable = false)
	private Long clubId;

	// 유저 아이디
	@Column(nullable = false)
	private List<Member> member;

	// 유저가 해당 클럽에서 작성한 글
	// @Column
	// private Article article;

	// 해당 클럽에서 퇴장당했을 때 재입장 제한을 위한 값
	@Column(nullable = false)
	private boolean banned;

	public void setMember(List<Member> member) {
		this.member = member;
	}
}

//	@Column(nullable = false)
//	private String authority;


	// 클럽 고유번호
	// 클럽명
	// 클럽 관리자(생성자)
	// 클럽 매니저
	// 클럽 유저

