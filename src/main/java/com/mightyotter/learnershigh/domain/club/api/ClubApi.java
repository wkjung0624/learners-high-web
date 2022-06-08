package com.mightyotter.learnershigh.domain.club.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClubApi {
	// # 클럽 관련
	// 클럽 생성 (+ 비밀번호 가능)
	// 클럽 정보 읽어오기 (이미지, 배너, 소개)
	// 클럽 페이지 입장 (클럽에 등록된 유저인지 확인)
	// 클럽 변경 (메인이미지, 이름, 소개글 <--(일반)--구분--(민감)--> 인원제한, 비공개 전환, 비밀번호 변경)
	// 클럽 삭제

	// # 클럽 유저 관련
	// 유저 입장(+ 비밀번호 가능)
	// 유저 초대(일회성 링크?)
	// 클럽 내 권한 승격 (관리자 및 생성자만)
	// 유저 강퇴

	// # 클럽 게시글 관련
	// 글 작성 (+ 비밀번호 or 권한 설정 가능)
	// 글 목록 불러오기
	// 글 내용 불러오기
	// 글 수정
	// 글 삭제
}
