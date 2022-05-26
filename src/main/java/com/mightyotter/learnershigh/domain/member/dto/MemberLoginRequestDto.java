package com.mightyotter.learnershigh.domain.member.dto;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLoginRequestDto {
	@NotNull
	private final String userId;
	@NotNull
	private final String userPw;

	@Builder
	public MemberLoginRequestDto(String userId, String userPw){
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
