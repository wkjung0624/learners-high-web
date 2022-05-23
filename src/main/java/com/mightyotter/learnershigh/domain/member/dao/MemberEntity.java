package com.mightyotter.learnershigh.domain.member.dao;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class MemberEntity {
	private int id;
	private String userId;
	private String userPw;
	private String nickName;
	private String email;
	private boolean verifiedEmail;
}
