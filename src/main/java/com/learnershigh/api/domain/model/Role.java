package com.learnershigh.api.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	ADMIN("ROLE_ADMIN", "관리자"),
	MANAGER("ROLE_MANAGER", "매니저"),
	MEMBER("ROLE_MEMBER", "사용자");

	private final String key;
	private final String value;
}
