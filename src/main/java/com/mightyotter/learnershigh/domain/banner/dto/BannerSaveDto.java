package com.mightyotter.learnershigh.domain.banner.dto;


import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import javax.persistence.Column;
import lombok.Builder;

@Builder
public class BannerSaveDto {
	private String bannerImageUrl;
	private String bannerHypertextLink;

	public Banner toEntity(){
		return Banner.builder()
			.bannerImageUrl(bannerImageUrl)
			.bannerHypertextLink(bannerHypertextLink)
			.build();
	}
}
