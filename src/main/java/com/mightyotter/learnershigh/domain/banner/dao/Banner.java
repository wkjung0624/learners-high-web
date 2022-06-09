package com.mightyotter.learnershigh.domain.banner.dao;

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

	@Column
	private boolean visible;
}
