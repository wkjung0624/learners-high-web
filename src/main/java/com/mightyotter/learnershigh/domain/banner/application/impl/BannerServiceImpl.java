package com.mightyotter.learnershigh.domain.banner.application.impl;

import com.mightyotter.learnershigh.domain.banner.dao.BannerType;
import com.mightyotter.learnershigh.global.util.FileUploadUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mightyotter.learnershigh.domain.banner.application.BannerService;
import com.mightyotter.learnershigh.domain.banner.dao.Banner;
import com.mightyotter.learnershigh.domain.banner.dao.BannerRepository;
import com.mightyotter.learnershigh.domain.banner.dto.BannerSaveDto;
import org.springframework.util.StringUtils;
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
	public Long addBanners(String type, BannerSaveDto bannerSaveDto) throws IllegalArgumentException, IOException {

		bannerSaveDto.setType(BannerType.valueOf(type.toUpperCase()).getValue()); // IllegalArgumentException point

		MultipartFile bannerImageFile = bannerSaveDto.getBannerImageFile();

		if(bannerImageFile == null) throw new FileNotFoundException("Banner image file not found");

		String uploadDir = "tmp/banner/thumbnail/";
		String fileName = StringUtils.cleanPath(
			Objects.requireNonNull(bannerImageFile.getOriginalFilename()));

		FileUploadUtil.saveFile(uploadDir,fileName,bannerImageFile);

		Banner banner = bannerSaveDto.toEntity();
		banner.setBannerImageSourceUrl(uploadDir + fileName);

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
