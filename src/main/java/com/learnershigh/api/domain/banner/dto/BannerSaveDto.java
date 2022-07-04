package com.learnershigh.api.domain.banner.dto;

import com.learnershigh.api.domain.banner.domain.Banner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@Builder
public class BannerSaveDto {
	private MultipartFile bannerImageFile;
	private String bannerHyperLinkReference;

	public Banner toEntity(){
		return Banner.builder()
			.bannerHyperLinkReference(bannerHyperLinkReference)
			.build();
	}
}
