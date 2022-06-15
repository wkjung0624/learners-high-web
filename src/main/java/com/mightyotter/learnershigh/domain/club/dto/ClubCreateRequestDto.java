package com.mightyotter.learnershigh.domain.club.dto;

import com.mightyotter.learnershigh.domain.club.dao.Club;
import lombok.Data;

@Data
public class ClubCreateRequestDto {
	private String master;
	private String clubName;
	private String password;
	private String mainImageUrlPath;
	private String description;
	private int maximumUserCount; // 클럽 활동성별 유저 제한?
	private boolean visible;

	public Club toEntity(){
		return Club.builder()
			.master(master)
			.clubName(clubName)
			.password(password)
			.mainImageUrlPath(mainImageUrlPath)
			.description(description)
			.maximumUserCount(maximumUserCount)
			.visible(visible)
			.deleted(false)
			.build();
	}
	// 추후에 클럽 관련된 테이블을 여러개로 분산하여서 저장? 그로 인한 이점은 무엇일지, 검색-색인 속도가 빨라질까?
}
