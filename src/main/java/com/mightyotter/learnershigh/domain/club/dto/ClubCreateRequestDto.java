package com.mightyotter.learnershigh.domain.club.dto;

import com.mightyotter.learnershigh.domain.club.dao.Club;;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
// javax.validation annotaion 문서 : https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html
@Data
public class ClubCreateRequestDto {
	private String master;
	private String clubName;
	private String password;
	private MultipartFile clubImageFile;
	private String description;
	private int maximumUserCount; // 클럽 활동성별 유저 제한?
	private boolean visible;

	public Club toEntity(){
		return Club.builder()
			.master(master)
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
