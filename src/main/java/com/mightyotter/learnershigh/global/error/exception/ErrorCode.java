package com.mightyotter.learnershigh.global.error.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	// INVALID_REFRESH_TOKEN(BAD_REQUEST, "INVALID_REFRESH_TOKEN", "리프레시 토큰이 유효하지 않습니다"),
	// MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "MISMATCH_REFRESH_TOKEN", "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
	// CANNOT_FOLLOW_MYSELF(BAD_REQUEST, "CANNOT_FOLLOW_MYSELF", "자기 자신은 팔로우 할 수 없습니다"),
	BAD_REQUEST_URI(BAD_REQUEST, "BAD_REQUEST_URI", "잘못된 API 요청입니다"),

	/* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
	INVALID_AUTH_TOKEN(UNAUTHORIZED, "INVALID_AUTH_TOKEN", "로그인 정보를 확인할 수 없습니다"),

	/* 403 FORBIDDEN : 인가되지 않은 사용자 */
	NO_PERMISSION_INFO(FORBIDDEN, "NO_PERMISSION_INFO", "권한이 없는 사용자입니다"),

	/* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
	MEMBER_NOT_FOUND(NOT_FOUND, "MEMBER_NOT_FOUND", "유저 정보를 찾을 수 없습니다"),
	REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "REFRESH_TOKEN_NOT_FOUND", "로그아웃 된 사용자입니다"),

	/* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
	DUPLICATE_RESOURCE(CONFLICT, "DUPLICATE_RESOURCE", "데이터가 이미 존재합니다"),

	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}