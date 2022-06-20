package com.mightyotter.learnershigh.domain.banner.api;

import java.io.IOException;
import java.util.List;
import com.mightyotter.learnershigh.domain.banner.application.BannerService;
import com.mightyotter.learnershigh.domain.banner.domain.Banner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class BannerApi {
	public final BannerService bannerService;

	// 배너 등록하기
	@PostMapping("/banner")
	public Long addBanner(
		@RequestParam String type, @RequestPart MultipartFile bannerImageFile, @RequestPart(required = false) String bannerHyperLinkReference)
		throws IllegalArgumentException, IOException {
		return bannerService.addBanners(type, bannerImageFile, bannerHyperLinkReference);
	}
	// 배너 정보 가져오기
	@GetMapping("/banner")
	public List<Banner> getAllBanners(@RequestParam String type){
		return bannerService.getAllBanners(type);
	}
	// 배너 표시여부 변경
	 @PostMapping("/banner/{bannerId}/visible")
	 public void changeBannerVisible(@PathVariable Long bannerId, @RequestParam boolean flag){
	     bannerService.changeBannerVisible(bannerId, flag);
	 }

	// 배너 삭제하기
	@PostMapping("/banner/{bannerId}/delete")
	public void deleteBanner(@PathVariable Long bannerId){
		bannerService.deleteBanners(bannerId);
	}
}
