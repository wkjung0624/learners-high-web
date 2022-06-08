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

	public List<Banner> getAllBanners(){
		return bannerRepository.findAll();
	}
	public Long addBanners(BannerSaveDto bannerSaveDto){
		return bannerRepository.save(bannerSaveDto.toEntity()).getBannerId();
	}
	public void deleteBanners(Long bannerId){
		bannerRepository.deleteById(bannerId);
	}
	public void updateBanners(){/*...*/}
}
