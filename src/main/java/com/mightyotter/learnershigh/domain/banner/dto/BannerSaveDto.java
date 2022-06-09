package com.mightyotter.learnershigh.domain.banner.dto;


import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class BannerSaveDto {
	private String type;
	private String bannerImageSourceUrl;
	private String bannerHyperLinkReference;

	public Banner toEntity(){
		return Banner.builder()
			.type(type)
			.bannerImageSourceUrl(bannerImageSourceUrl)
			.bannerHyperLinkReference(bannerHyperLinkReference)
			.build();
	}
}
