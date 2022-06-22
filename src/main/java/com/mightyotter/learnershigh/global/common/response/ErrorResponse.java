package com.mightyotter.learnershigh.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// Response Entity 사용 이유 와 @RestAdvice 동작 구조 : https://woodcock.tistory.com/19
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {
	private String code;
	private String message;
	private String detail;
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