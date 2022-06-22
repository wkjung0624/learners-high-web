package com.mightyotter.learnershigh.global.common.response.data;

import lombok.Builder;
import lombok.Getter;

// 구글 JSON 스타일 가이드 https://google.github.io/styleguide/jsoncstyleguide.xml

@Builder
@Getter
public final class DataLayer {
	// boolean 는 객체가 아니어서 클래스 멤버변수 선언 시 기본적으로 false 으로 초기화됨, 그로인해 Response Body 에 해당 값이 같이 리턴되는 문제발생
	// 임시적으로 래퍼클래스 Boolean 으로 변환하여 해결
	private final String kind;
	private final String fields;
	private final String etag;
	private final String id;
	private final String lang;
	private final String updated; // date formatted RFC 3339
	private final Boolean deleted;
	private final Object items;
}
