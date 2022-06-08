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

	@Column(columnDefinition = "TEXT", nullable = false)
	private String bannerImageUrl;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String bannerHypertextLink;
}
