package com.mightyotter.learnershigh.domain.banner.dao;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long bannerId;

	// 배너 구분 (상단 : TOP, 메인 carusel : MAIN)
	@Column
	private String type;

	// 배너 이미지 소스
	@Column(columnDefinition = "TEXT", nullable = false)
	private String bannerImageSourceUrl;

	// 배너 클릭시 연결될 하이퍼링크
	@Column(columnDefinition = "TEXT", nullable = false)
	private String bannerHypertextLink;

	// 배너 개제 예정일
//	@Column
//	private Date reservedDate;
	// 배너 자동 비공개 전환 예정일
//	@Column
//	private Date expireDate;

	@Column
	private boolean visible;
}
