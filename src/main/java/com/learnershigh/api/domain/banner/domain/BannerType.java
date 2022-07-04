package com.learnershigh.api.domain.banner.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BannerType {
	ALL("all"),
	NAVIGATION("nav"),
	MAIN("main");

	private final String value;
}
