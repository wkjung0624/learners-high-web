package com.mightyotter.learnershigh.domain.member.dto;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class MemberCreateRequestDto {
	@NotNull
	private final String userId;
	@NotNull
	private final String userPw;

	@NotNull
	private final String nickName;

	@Email
	@Getter
	@NotNull
	private final String email;

	@Builder
	public MemberCreateRequestDto(String userId, String userPw, String nickName, String email){
		this.userId = userId;
		this.userPw = userPw;
		this.nickName = nickName;
		this.email = email;
	}

	public Member toEntity(){
		return Member.builder()
			.userId(userId)
			.userPw(userPw)
			.nickName(nickName)
			.email(email)
			.verifiedEmail(false)
			.build();
	}
}
