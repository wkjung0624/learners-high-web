package com.mightyotter.learnershigh.domain.banner.api;

import com.mightyotter.learnershigh.domain.banner.application.BannerService;
import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import com.mightyotter.learnershigh.domain.banner.dto.BannerSaveDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class BannerApi {
	public final BannerService bannerService;

	@GetMapping("/banner")
	public List<Banner> getAllBanners(){
		return bannerService.getAllBanners();
	}
	@PostMapping("/banner")
	public Long addBanner(BannerSaveDto bannerSaveDto){
		return bannerService.addBanners(bannerSaveDto);
	}
	@PostMapping("/banner/{bannerId}/delete")
	public void deleteBanner(@PathVariable Long bannerId){
		bannerService.deleteBanners(bannerId);
	}
}
