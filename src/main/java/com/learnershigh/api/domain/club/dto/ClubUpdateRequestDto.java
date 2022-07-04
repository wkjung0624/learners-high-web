package com.learnershigh.api.domain.club.dto;

import com.learnershigh.api.domain.club.domain.Club;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ClubUpdateRequestDto {
	private String clubName;
	private String password;
	private MultipartFile clubImageFile;
	private String description;
	private int maximumUserCount; // 클럽 활동성별 유저 제한?
	private boolean visible;

	public Club toEntity(){
		return Club.builder()
			.clubName(clubName)
			.password(password)
			.description(description)
			.maximumUserCount(maximumUserCount)
			.visible(visible)
			.deleted(false)
			.build();
	}
	// 추후에 클럽 관련된 테이블을 여러개로 분산하여서 저장? 그로 인한 이점은 무엇일지, 검색-색인 속도가 빨라질까?
}
