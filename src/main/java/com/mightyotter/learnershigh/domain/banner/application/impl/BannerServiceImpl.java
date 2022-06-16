package com.mightyotter.learnershigh.domain.banner.application.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mightyotter.learnershigh.domain.banner.application.BannerService;
import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import com.mightyotter.learnershigh.domain.banner.dao.BannerRepository;
import com.mightyotter.learnershigh.domain.banner.dto.BannerSaveDto;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {
	public final BannerRepository bannerRepository;
	@Override
	public List<Banner> getAllBanners(String type){
		if("all".equals(type)){
			return bannerRepository.findAll();
		}
		return bannerRepository.findByType(type);
	}
	@Override
	public Long addBanners(String type, BannerSaveDto bannerSaveDto){
		bannerSaveDto.setType(type);
		return bannerRepository.save(bannerSaveDto.toEntity()).getBannerId();
	}
	@Override
	public void changeBannerVisible(Long bannerId, boolean flag){
		Banner banner = bannerRepository.getById(bannerId);
		banner.setVisible(flag);
		bannerRepository.save(banner);
	}
	@Override
	public void deleteBanners(Long bannerId){
		bannerRepository.deleteById(bannerId);
	}
}
