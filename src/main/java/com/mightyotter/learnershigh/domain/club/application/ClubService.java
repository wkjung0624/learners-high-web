package com.mightyotter.learnershigh.domain.club.application;

import com.mightyotter.learnershigh.domain.club.dao.Club;
import com.mightyotter.learnershigh.domain.club.dto.ClubCreateRequestDto;
import com.mightyotter.learnershigh.domain.club.dto.ClubUpdateRequestDto;
import com.mightyotter.learnershigh.domain.club.exception.DuplicatedClubNameException;
import java.util.List;

public interface ClubService {

	// Boolean 으로 조건을 검사하는 형태가 아닌 Exception 처리를 하는 이유는?
	// 떠넘긴 예외는 어떻게 처리해야할까? : https://mangkyu.tistory.com/204
	// 예외처리를 조금 더 깔끔하게 https://bcp0109.tistory.com/303
	// 예외처리를 조금 더 효율적으로 https://tecoble.techcourse.co.kr/post/2020-08-17-custom-exception/

	Long createClub(ClubCreateRequestDto clubCreateRequestDto) throws Exception;
	boolean checkClubNameDuplication(String clubName) throws DuplicatedClubNameException;
	List<Club> getClubList();
	Club getClubDetail(Long clubId);
	void enterClub(Long clubId, String password);
	Club updateClubInfo(Long clubId, ClubUpdateRequestDto clubUpdateRequestDto);
	boolean delegateClubMasterPrivileges(Long clubId, String delegatedUsername);
	void deleteClub(Long clubId);
	void getClubChannelList();
	void withdrawClubMembership();
	void inviteClubMember();
	void approveClubMembershipRequest();
	void grantClubMemberPrivileges();
	void kickClubMember();
	void createClubChannel();
	void updateClubChannelInfo();
	void deleteClubChannel();
	void addClubPost();
	void getClubPostList();
	void getClubPostDetail();
	void updateClubPost();
	void removeClubPost();
}