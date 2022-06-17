package com.mightyotter.learnershigh.domain.banner.application;

import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface BannerService {
	List<Banner> getAllBanners(String type);
	Long addBanners(String type, MultipartFile bannerImageFile, String bannerHyperLinkReference) throws IllegalArgumentException, IOException;
	void changeBannerVisible(Long bannerId, boolean flag);
	void deleteBanners(Long bannerId);
}
