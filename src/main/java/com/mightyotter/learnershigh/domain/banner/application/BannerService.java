package com.mightyotter.learnershigh.domain.banner.application;

import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import com.mightyotter.learnershigh.domain.banner.dto.BannerSaveDto;
import java.util.List;
public interface BannerService {
	List<Banner> getAllBanners(String type);
	Long addBanners(String type, BannerSaveDto bannerSaveDto);
	void changeBannerVisible(Long bannerId, boolean flag);
	void deleteBanners(Long bannerId);
}
