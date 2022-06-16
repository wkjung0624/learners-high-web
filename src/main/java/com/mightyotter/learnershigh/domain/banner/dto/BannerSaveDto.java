package com.mightyotter.learnershigh.domain.banner.dto;

import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@Builder
public class BannerSaveDto {
	private String type;
	private MultipartFile bannerImageFile;
	private String bannerHyperLinkReference;

	public Banner toEntity(){
		return Banner.builder()
			.type(type)
			.bannerHyperLinkReference(bannerHyperLinkReference)
			.build();
	}
}
