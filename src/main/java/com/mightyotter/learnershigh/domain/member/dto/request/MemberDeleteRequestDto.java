package com.mightyotter.learnershigh.domain.member.dto.request;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDeleteRequestDto {
	//TODO: 'MemberLoginRequestDto' 와 동일함, Delete와 Login DTO 두 개를 분리시켜야만 하는 부분이 있을까?
	private String userId;
	private String userPw;

	@Builder
	public MemberDeleteRequestDto(String userId, String userPw){
		this.userId = userId;
		this.userPw = userPw;
	}

	public Member toEntity(){
		return Member.builder()
			.userId(userId)
			.userPw(userPw)
			.build();
	}
}
