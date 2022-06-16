package com.mightyotter.learnershigh.domain.banner.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BannerType {
	ALL("all"),NAVIGATION("nav"),MAIN("main");

	private final String value;
}
