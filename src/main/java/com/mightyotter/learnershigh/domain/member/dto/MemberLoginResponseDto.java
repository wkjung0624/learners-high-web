package com.mightyotter.learnershigh.domain.member.dto;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class MemberLoginResponseDto {
	@NotNull
	private final String userId;


	@NotNull
	private final String nickName;

	@Email
	@Getter
	@NotNull
	private final String email;

	@Builder
	public MemberLoginResponseDto(String userId, String nickName, String email){
		this.userId = userId;
		this.nickName = nickName;
		this.email = email;
	}

	public Member toEntity(){
		return Member.builder()
			.userId(userId)
			.nickName(nickName)
			.email(email)
			.build();
	}
}
