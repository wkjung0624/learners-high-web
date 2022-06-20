package com.mightyotter.learnershigh.domain.banner.application.impl;

import com.mightyotter.learnershigh.domain.banner.domain.BannerType;
import com.mightyotter.learnershigh.global.util.FileExtensionValidationUtil;
import com.mightyotter.learnershigh.global.util.FileUploadUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mightyotter.learnershigh.domain.banner.application.BannerService;
import com.mightyotter.learnershigh.domain.banner.domain.Banner;
import com.mightyotter.learnershigh.domain.banner.dao.BannerRepository;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {
	public final BannerRepository bannerRepository;
	@Override
	public List<Banner> getAllBanners(String type){
		// enum 에서 .equals 를 쓸 필요없는 이유
		// - https://webfirewood.tistory.com/132

		if(BannerType.ALL == BannerType.valueOf(type.toUpperCase())) return bannerRepository.findAll();

		return bannerRepository.findByType(BannerType.valueOf(type.toUpperCase()).getValue());
	}
	@Override
	public Long addBanners(String type, MultipartFile bannerImageFile, String bannerHyperLinkReference) throws IllegalArgumentException, IOException {

		if (bannerImageFile == null)
			throw new FileNotFoundException("Banner image file not found");
		if (bannerHyperLinkReference == null)
			bannerHyperLinkReference = "/";
		String uploadDir = "tmp/banner/thumbnail/";

		// 파일명을 UUID 로 변환하는 이유
		// 1. 파일명이 곂칠 수 있음
		// 2. 파일명이 한글인 경우 윈도우, 맥 환경이 다름
		// 3. 브라우저에서 인식하는데 아무래도 숫자+영어가 나을듯

		// 파일명 생성하는 부분은 분리해서 함수로 구현해야할까?
		// 파일에 대한 검증 필요 (허용된 확장자 여부, Content-Type = 확장자 = 파일 매직넘버[hex value] 간 일치 여부)
		// 확장자 구분자 '.' 가 여럿인 경우 처리필요 (RCE 위협 요소)
		// 	- https://medium.com/@jr.mayank1999/rce-via-image-jpg-png-file-upload-8e4ad8a2100)
		String fileExt = FileExtensionValidationUtil.match("image",bannerImageFile.getBytes());
		if(fileExt == null) throw new IllegalArgumentException("Not Allowed File Extension");
		String fileName = UUID.randomUUID() + fileExt;

		// 파일 생성 실패시 Rollback 필요
		FileUploadUtil.saveFile(uploadDir,fileName,bannerImageFile);

		Banner banner = Banner.builder()
			.type(BannerType.valueOf(type.toUpperCase()).getValue()) // IllegalArgumentException point
			.bannerImageSourceUrl(uploadDir+fileName)
			.bannerHyperLinkReference(bannerHyperLinkReference)
			.visible(true)
			.build();

		return bannerRepository.save(banner).getBannerId();
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
