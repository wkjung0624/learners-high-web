package com.mightyotter.learnershigh.domain.member.dto;

import com.mightyotter.learnershigh.domain.member.dao.MemberEntity;

public class MemberDTO {
	private String userId;
	private String userPw;
	private String email;

	public MemberDTO(final MemberEntity entity) {
		this.userId = entity.getUserId();
		this.userPw = entity.getUserPw();
		this.email = entity.getEmail();
	}
}
