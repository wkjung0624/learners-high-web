package com.learnershigh.api.domain.banner.api;

import com.learnershigh.api.domain.banner.domain.Banner;
import com.learnershigh.api.global.common.response.StandardResponseBody;
import com.learnershigh.api.global.common.response.data.DataLayer;
import java.io.IOException;
import java.util.List;
import com.learnershigh.api.domain.banner.application.BannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@Secured("ROLE_MANAGER") // 권한이 'ROLE_MANAGER' 이상만 API 메소드에 접근 가능하도록 설정
public class BannerApi {
	public final BannerService bannerService;

	// 배너 등록하기
	@PostMapping("/banner")
	public ResponseEntity<StandardResponseBody> addBanner(
		@RequestParam String type, @RequestPart MultipartFile bannerImageFile, @RequestPart(required = false) String bannerHyperLinkReference)
		throws IllegalArgumentException, IOException {

		bannerService.addBanners(type, bannerImageFile, bannerHyperLinkReference);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(StandardResponseBody.builder()
				.data(DataLayer.builder()
					.status(true)
					.build())
				.build());
	}
	// 배너 정보 가져오기
	@GetMapping("/banner")
	public ResponseEntity<StandardResponseBody> getAllBanners(@RequestParam String type){

		List<Banner> bannerList = bannerService.getAllBanners(type);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(StandardResponseBody.builder()
				.data(DataLayer.builder()
					.items(bannerList)
					.build())
				.build());
	}
	// 배너 표시여부 변경
	 @PostMapping("/banner/{bannerId}/visible")
	 public ResponseEntity<StandardResponseBody> changeBannerVisible(@PathVariable Long bannerId, @RequestParam boolean flag){
	     bannerService.changeBannerVisible(bannerId, flag);

		 return ResponseEntity
			 .status(HttpStatus.NO_CONTENT)
			 .body(StandardResponseBody.builder()
				 .data(DataLayer.builder()
					 .status(true)
					 .build())
				 .build());
	 }

	// 배너 삭제하기
	@PostMapping("/banner/{bannerId}/delete")
	public ResponseEntity<StandardResponseBody> deleteBanner(@PathVariable Long bannerId){
		bannerService.deleteBanners(bannerId);

		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(StandardResponseBody.builder()
				.data(DataLayer.builder()
					.status(false)
					.build())
				.build());
	}
}
