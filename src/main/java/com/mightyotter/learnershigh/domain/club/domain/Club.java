package com.mightyotter.learnershigh.domain.club.domain;

import com.mightyotter.learnershigh.domain.model.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Club extends BaseTimeEntity {
	@Id	@Column(name = "CLUB_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clubId;

	// 최초 생성자 ID 또는 마스터 권한 위임자(Member 클래스로 매핑 필요)
	@Column(nullable = false)
	private String master;

	// 클럽명
	@Column(nullable = false)
	private String clubName;

	// 클럽 입장 시 필요한 비밀번호
	@Column
	private String password;

	// 클럽 메인 이미지 저장 주소
	@Column
	private String mainImageUrlPath;

	// 클럽 메인 이미지 파일명
	@Column
	private String mainImageFileName;

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
