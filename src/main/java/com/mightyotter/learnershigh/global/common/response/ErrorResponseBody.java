package com.mightyotter.learnershigh.global.common.response;

import lombok.Builder;
import lombok.Getter;

// Response Entity 사용 이유 와 @RestAdvice 동작 구조 : https://woodcock.tistory.com/19
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
// 구글 JSON 스타일 가이드 https://google.github.io/styleguide/jsoncstyleguide.xml

// @AllArgsConstructor 를 사용할수도 있으나 Builder 패턴 하나만 쓰는 것으로 통일
@Builder
@Getter
public final class ErrorResponseBody {
	private final String code;
	private final String message;
	private final String detail;
}

/*
object {
    integer code?;
    string message?;
    array [
      object {
        string domain?;
        string reason?;
        string message?;
        string location?;
        string locationType?;
        string extendedHelp?;
        string sendReport?;
      }*;
    ] errors?;
  }* error?;
}
 */