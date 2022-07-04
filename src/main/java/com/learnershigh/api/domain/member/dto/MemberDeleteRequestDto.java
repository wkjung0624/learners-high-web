package com.learnershigh.api.domain.member.dto;

import com.learnershigh.api.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDeleteRequestDto {
	//TODO: 'MemberLoginRequestDto' 와 동일함, Delete와 Login DTO 두 개를 분리시켜야만 하는 부분이 있을까?
	private String username;
	private String password;

	@Builder
	public MemberDeleteRequestDto(String username, String password){
		this.username = username;
		this.password = password;
	}

	public Member toEntity(){
		return Member.builder()
			.username(username)
			.password(password)
			.build();
	}
}
