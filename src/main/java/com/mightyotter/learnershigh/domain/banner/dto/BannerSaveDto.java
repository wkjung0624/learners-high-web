package com.mightyotter.learnershigh.domain.banner.dto;


import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import javax.persistence.Column;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class BannerSaveDto {
	private String type;
	private String bannerImageSourceUrl;
	private String bannerHypertextLink;

	public Banner toEntity(){
		return Banner.builder()
			.type(type)
			.bannerImageSourceUrl(bannerImageSourceUrl)
			.bannerHypertextLink(bannerHypertextLink)
			.build();
	}
}
