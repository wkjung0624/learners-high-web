package com.mightyotter.learnershigh.domain.banner.domain;

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
	@Column
	private String type;
	// 배너 이미지 소스
	@Column(columnDefinition = "TEXT", nullable = false)
	private String bannerImageSourceUrl;
	// 배너 클릭시 연결될 하이퍼링크
	@Column
	private String bannerHyperLinkReference;
	// 배너 개제 예정일 (이부분을 BaseTimeEntity 같이 임플리먼테이션으로?)
//	@Column
//	private Date reservedDate;
	// 배너 자동 비공개 전환 예정일
//	@Column
//	private Date expireDate;

	@Column
	private boolean visible;
}
