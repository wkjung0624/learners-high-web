package com.mightyotter.learnershigh.domain.member.dto;

import com.mightyotter.learnershigh.domain.member.domain.Member;
import com.mightyotter.learnershigh.domain.model.Role;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberCreateRequestDto {
	@NotNull
	private final String username;
	@NotNull
	private final String password;
	@NotNull
	private final String nickname;

	@Email
	@NotNull
	private final String email;

	public Member toEntity(){
		return Member.builder()
			.username(username)
			.password(password)
			.nickname(nickname)
			.email(email)
			.verifiedEmail(false)
			.role(Role.MEMBER)
			.build();
	}
}
