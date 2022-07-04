package com.learnershigh.api.domain.banner.dao;

import com.learnershigh.api.domain.banner.domain.Banner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

	// 배너 타입을 기준으로 검색 (Main, Top)
	List<Banner> findByType(String type);
}
