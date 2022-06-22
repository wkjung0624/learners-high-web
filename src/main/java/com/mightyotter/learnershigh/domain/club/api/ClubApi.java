package com.mightyotter.learnershigh.domain.club.api;

import com.mightyotter.learnershigh.domain.club.application.impl.ClubServiceImpl;
import com.mightyotter.learnershigh.domain.club.domain.Club;
import com.mightyotter.learnershigh.domain.club.dto.ClubCreateRequestDto;
import com.mightyotter.learnershigh.domain.club.dto.ClubUpdateRequestDto;
import com.mightyotter.learnershigh.domain.club.exception.DuplicatedClubNameException;
import com.mightyotter.learnershigh.global.common.response.StandardResponseBody;
import com.mightyotter.learnershigh.global.common.response.data.DataLayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import java.io.IOException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ClubApi {

	private final ClubServiceImpl clubServiceImpl;

	// x - multipart 와 dto 객체를 동시에 쓰고싶을때는? : https://myunji.tistory.com/488
	// dto 에서 multipart 를 같이 받아오고싶을때는 ? : https://pooney.tistory.com/10
	@PostMapping("/club")
	public ResponseEntity<StandardResponseBody> createClub(ClubCreateRequestDto clubCreateRequestDto) throws DuplicatedClubNameException, IOException, NullPointerException {
		clubServiceImpl.createClub(clubCreateRequestDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(StandardResponseBody.builder()
				.data(DataLayer.builder()
					.status(true)
					.build())
				.build());
	}

	// ResponseEntity 에 제네릭 파라미터를 지정해주지 않는다면 어떤 문제가 발생하는가?
	@GetMapping("/club")
	public ResponseEntity<StandardResponseBody> getClubList() {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				StandardResponseBody.builder()
					.apiVersion("0.1")
					.context("club")
					.id(0L)
					.data(
						DataLayer.builder()
							.items(clubServiceImpl.getClubList())
							.build())
					.build());

	}

	@GetMapping("/club/{clubId}")
	public ResponseEntity<StandardResponseBody> getClubDetail(@PathVariable Long clubId) {
		Club club = clubServiceImpl.getClubDetail(clubId);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				StandardResponseBody.builder()
					.data(
						DataLayer.builder()
							.items(club)
							.build())
					.build());
	}

	@GetMapping("/club/{clubId}/enter")
	public ResponseEntity<StandardResponseBody> enterClub(@PathVariable Long clubId, @RequestParam(required=false) String password) {
		clubServiceImpl.enterClub(clubId, password); // bool 형태로 리턴해줘야 할 듯
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				StandardResponseBody.builder()
					.data(
						DataLayer.builder()
							.status(true)
							.build())
					.build());
 	}

	@PostMapping("/club/{clubId}/update")
	public ResponseEntity<StandardResponseBody> updateClubInfo(@PathVariable Long clubId, ClubUpdateRequestDto clubUpdateRequestDto) {
		clubServiceImpl.updateClubInfo(clubId, clubUpdateRequestDto);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(
				StandardResponseBody.builder()
					.data(DataLayer.builder()
						.status(true)
						.build())
					.build());
	}

	@PostMapping("/club/{clubId}/delegate")// ?delegatedUsername="username"
	public ResponseEntity<StandardResponseBody> delegateClubMasterPrivileges(@PathVariable Long clubId, @RequestBody Map<String, String> requestBody) {
		clubServiceImpl.delegateClubMasterPrivileges(clubId, requestBody.get("delegatedUsername"));
		// bool 형태로 리턴처리, 예외처리-유효하지않은클럽id,인가되지않은 클럽접근, 인가되지않은 변경권한, 자신을지정함, 유저찾기실패 등
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(StandardResponseBody.builder()
				.data(DataLayer.builder()
					.status(true)
					.build())
				.build());
	}

	@PostMapping("/club/{clubId}/delete")
	public ResponseEntity<StandardResponseBody> deleteClub(@PathVariable Long clubId) {
		clubServiceImpl.deleteClub(clubId);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(StandardResponseBody.builder()
				.data(DataLayer.builder()
					.status(true)
					.build())
				.build());
	}
//
//	@GetMapping("/club/{clubId}/channel")
//	public void getClubChannelList() {
//		clubServiceImpl.getClubChannelList();
//	}
//
//	@PostMapping("/club/{clubId}/channel/{channelId}/withdraw")
//	public void withdrawClubMembership() {
//
//	}
//
//	// 초대 링크 생성 (jwt 로 파라미터 생성하기, 생성과 동시에 In-Memory Storage 에 저장 (만료시간 1시간))
//	// Redis 저장 키 값은 invite:club:{클럽ID}:{iss값}:{aud값}
//	// jwt payload 내용 >>
//	/*
//		iss(이슈어):(초대발행자아이디),
//		aud(청중):초대받는사람,
//		iat(발행일):linuxTime,
//		exp(만료일):linuxTime
//	 */
//	// jwt 공식 키 : https://datatracker.ietf.org/doc/html/rfc7519#section-4.1
//	@PostMapping("/club/{clubId}/channel/{channelId}/invite")
//	public void inviteClubMember() {
//		// /club/{clubId}/channel/{channelId}/join?
//	}
//	@PostMapping("/club/{clubId}/channel/{channelId}/withdraw")
//	public void approveClubMembershipRequest() {
//	}
//	@PostMapping("/club/{clubId}/member/{memberId}/elevate")
//	public void grantClubMemberPrivileges() {
//
//	}
//	@PostMapping("/club/{clubId}/member/{memberId}/kick")
//	public void kickClubMember() {
//
//	}
//	@PostMapping("/club/{clubId}/channel")
//	public void createClubChannel() {
//
//	}
//
//	@PostMapping("/club/{clubId}/channel/{channelId}/update")
//	public void updateClubChannelInfo() {
//
//	}
//
//
//	@PostMapping("/club/{clubId}/channel/{channelId}/delete")
//	public void deleteClubChannel() {
//
//	}
//
//	@PostMapping("/club/{clubId}/channel/{channelId}/post")
//	public void addClubPost() {
//
//	}
//
//	@GetMapping("/club/{clubId}/channel/{channelId}/post")
//	public void getClubPostList() {
//
//	}
//
//	@PostMapping("/club/{clubId}/channel/{channelId}/post")
//	public void getClubPostDetail() {
//
//	}
//
//	@PostMapping("/club/{clubId}/channel/{channelId}/post/{postId}/update")
//	public void updateClubPost() {
//
//	}
//
//	@PostMapping("/club/{clubId}/channel/{channelId}/post/{postId}/delete")
//	public void removeClubPost() {
//
//	}
	
	/*
	# 공통
		클럽 리스트 조회
		특정 클럽 상세정보 조회 (이미지, 배너, 소개)
		그룹 입장 요청 (클럽에 등록된 유저인지 확인)

	1 클럽 관리자
		1-1 클럽 관리 관련
			클럽 생성 (+ 비밀번호 가능)
			클럽 정보 변경 (메인이미지, 이름, 소개글 <--(일반)--구분--(민감)--> 인원제한, 비공개 전환, 비밀번호 변경)
			클럽장 권한 위임
			클럽 삭제
		1-2 클럽 내 멤버 관리
			멤버 초대
			그룹 가입 요청 승인
			멤버 권한 승격
			멤버 추방
		1-3 그룹 내 채널 관리 (가장 마지막에 작업하기. 우선도 낮음)
			채널 생성
			채널 조회
			채널 삭제
			채널 수정

	3 클럽 멤버
		3-1 일반
			클럽 탈퇴
		3-1 채널 내 글쓰기 (가장 마지막에 작업하기. 우선도 낮음)
			글 작성 (+ 비밀번호 or 권한 설정 가능)
			글 목록 불러오기
			글 내용 불러오기
			글 수정
			글 삭제

	 */
}
