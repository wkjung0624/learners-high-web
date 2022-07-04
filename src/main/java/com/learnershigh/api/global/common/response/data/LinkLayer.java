package com.learnershigh.api.global.common.response.data;

import lombok.Builder;
import lombok.Getter;

// 구글 JSON 스타일 가이드 https://google.github.io/styleguide/jsoncstyleguide.xml

@Builder
@Getter
public final class LinkLayer {
	private final Object self;
	private final String selfLink;
	private final Object edit;
	private final String editLink;
	private final Object next;
	private final String nextLink;
	private final Object previous;
	private final String previousLink;
}
