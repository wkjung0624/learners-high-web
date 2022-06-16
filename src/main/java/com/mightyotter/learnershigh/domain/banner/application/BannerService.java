package com.mightyotter.learnershigh.domain.banner.application;

import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import com.mightyotter.learnershigh.domain.banner.dto.BannerSaveDto;
import java.io.IOException;
import java.util.List;

public interface BannerService {
	List<Banner> getAllBanners(String type);
	Long addBanners(String type, BannerSaveDto bannerSaveDto) throws IllegalArgumentException, IOException;
	void changeBannerVisible(Long bannerId, boolean flag);
	void deleteBanners(Long bannerId);
}
