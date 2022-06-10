package com.mightyotter.learnershigh.domain.club.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
public class Club {
	@Id	@Column(name = "CLUB_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clubId;

	// 최초 생성자 ID
	@Column(nullable = false)
	private String creator;

	// 클럽명
	@Column(nullable = false)
	private String name;

	// 클럽 입장 시 필요한 비밀번호
	@Column
	private String passwd;

	// 클럽 메인 이미지 주소
	@Column
	private String mainImageUrlPath;

	// 클럽 소개글
	@Column
	private String description;

	// 입장 가능한 최대 인원 수
	@Column
	private int maximumUserCount;

	// 외부 공개 여부
	@Column(nullable = false)
	private boolean visible;

	// 삭제 여부
	@Column
	private boolean deleted;

	// 추후에 클럽 관련된 테이블을 여러개로 분산하여서 저장? 그로 인한 이점은 무엇일지, 검색-색인 속도가 빨라질까?
}
