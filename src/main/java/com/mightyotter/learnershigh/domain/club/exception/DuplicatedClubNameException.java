package com.mightyotter.learnershigh.domain.club.exception;

public class DuplicatedClubNameException extends Exception {
	//6~10줄 참고 코드_예외처리방법 : https://dev-jejeb.tistory.com/50
	// 생성자의 인자로 예외메세지를 전달받아서
	public DuplicatedClubNameException(String msg){
		// 부모생성자에 전달하면
		super(msg);
		// message 필드에 저장됨
	}
}
