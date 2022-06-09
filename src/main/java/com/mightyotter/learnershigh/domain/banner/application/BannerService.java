package com.mightyotter.learnershigh.domain.banner.application;

import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import com.mightyotter.learnershigh.domain.banner.dao.BannerRepository;
import com.mightyotter.learnershigh.domain.banner.dto.BannerSaveDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BannerService {
	public final BannerRepository bannerRepository;

	public List<Banner> getAllBanners(String type){
		return bannerRepository.findByType(type);
	}
	public Long addBanners(String type, BannerSaveDto bannerSaveDto){
		bannerSaveDto.setType(type);
		return bannerRepository.save(bannerSaveDto.toEntity()).getBannerId();
	}
	public void changeBannerVisible(){/*...*/}
	public void deleteBanners(Long bannerId){
		bannerRepository.deleteById(bannerId);
	}
}
