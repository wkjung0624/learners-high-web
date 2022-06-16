package com.mightyotter.learnershigh.domain.club.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ClubExceptionController {
	@ExceptionHandler(DuplicatedClubNameException.class)
	public String dupEx(DuplicatedClubNameException exception){
		return exception.getMessage();
	}
}
