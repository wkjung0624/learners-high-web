package com.learnershigh.api.domain.banner.application;

import com.learnershigh.api.domain.banner.domain.Banner;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface BannerService {
	List<Banner> getAllBanners(String type);
	Long addBanners(String type, MultipartFile bannerImageFile, String bannerHyperLinkReference) throws IllegalArgumentException, IOException;
	void changeBannerVisible(Long bannerId, boolean flag);
	void deleteBanners(Long bannerId);
}
