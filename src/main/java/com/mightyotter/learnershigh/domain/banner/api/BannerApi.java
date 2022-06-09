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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class BannerApi {
	public final BannerService bannerService;

	// 배너 등록하기
	@PostMapping("/banner")
	public Long addBanner(@RequestParam String bannerType, BannerSaveDto bannerSaveDto){
		return bannerService.addBanners(bannerType, bannerSaveDto);
	}
	// 배너 정보 가져오기
	@GetMapping("/banner")
	public List<Banner> getAllBanners(@RequestParam String bannerType){
		return bannerService.getAllBanners(bannerType);
	}
	// 배너 표시여부 변경
	// @PostMapping("/banner/visible")
	// public void changeBannerVisible(){
	//     bannerService.changeBannerVisible();
	// }

	// 배너 삭제하기
	@PostMapping("/banner/{bannerId}/delete")
	public void deleteBanner(@PathVariable Long bannerId){
		bannerService.deleteBanners(bannerId);
	}
}
