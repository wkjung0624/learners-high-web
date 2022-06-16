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
		if("all".equals(type)){
			return bannerRepository.findAll();
		}
		return bannerRepository.findByType(type);
	}
	public Long addBanners(String type, BannerSaveDto bannerSaveDto){
		bannerSaveDto.setType(type);
		return bannerRepository.save(bannerSaveDto.toEntity()).getBannerId();
	}
	public void changeBannerVisible(Long bannerId, boolean flag){
		Banner banner = bannerRepository.getById(bannerId);
		banner.setVisible(flag);
		bannerRepository.save(banner);
	}
	public void deleteBanners(Long bannerId){
		bannerRepository.deleteById(bannerId);
	}
}