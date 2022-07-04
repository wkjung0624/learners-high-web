package com.learnershigh.api.domain.club.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PermissionLevel {

	MASTER(0),
	OPERATOR(10),
	MEMBER(99);

	private final int level;
}
