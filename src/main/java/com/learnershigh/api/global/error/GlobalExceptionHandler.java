package com.learnershigh.api.global.error;

import com.learnershigh.api.domain.club.exception.DuplicatedClubNameException;
import com.learnershigh.api.global.common.response.ErrorResponseBody;
import com.learnershigh.api.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Exception Guide - https://github.com/cheese10yun/spring-guide/blob/master/docs/exception-guide.md
// 커스텀 예외처리 방법 https://bloowhale.tistory.com/72
// 예외에 부가적인 정보를 추가하여 넘겨주는 방법은?

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

	/* Common Exception Start */
	/* Common Exception End */

	/* Article Exception Start */
	/* Article Exception End */

	/* Banner Exception Start*/
	/* Banner Exception End*/

	/* Club Exception Start*/
	@ExceptionHandler(DuplicatedClubNameException.class)
	protected ResponseEntity<ErrorResponseBody> duplicatedClubNameException (DuplicatedClubNameException e){
		ErrorCode errorCode = ErrorCode.DUPLICATE_RESOURCE;
		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(ErrorResponseBody.builder()
				.code(errorCode.getCode())
				.message(errorCode.getMessage())
				.detail(e.getMessage())
				.build());
	}
	/* Club Exception End*/

	/* Member Exception Start*/
	/* Member Exception End*/

	/* Infra Exception Start */
	/* Infra Exception End */
}
