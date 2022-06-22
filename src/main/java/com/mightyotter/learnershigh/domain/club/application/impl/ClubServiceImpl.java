package com.mightyotter.learnershigh.domain.club.application.impl;

import com.mightyotter.learnershigh.domain.club.application.ClubService;
import com.mightyotter.learnershigh.domain.club.domain.Club;
import com.mightyotter.learnershigh.domain.club.dao.ClubRepository;
import com.mightyotter.learnershigh.domain.club.dto.ClubCreateRequestDto;
import com.mightyotter.learnershigh.domain.club.dto.ClubUpdateRequestDto;
import com.mightyotter.learnershigh.domain.club.exception.DuplicatedClubNameException;
import com.mightyotter.learnershigh.global.util.FileUploadUtil;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
@RequiredArgsConstructor
@Service
public class ClubServiceImpl implements ClubService {
	private final ClubRepository clubRepository;
	// [관리자 - 클럽 관리] - 클럽 생성(+비밀번호 가능)
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long createClub(@RequestBody ClubCreateRequestDto clubCreateRequestDto)
		throws DuplicatedClubNameException, IOException {
		// Authenticated 된 유저만 생성 가능
		// Anonymous 는 제외
		if (checkClubNameDuplication(clubCreateRequestDto.getClubName())){
			clubCreateRequestDto.setMaster("testMaster");
			return clubRepository.save(clubCreateRequestDto.toEntity()).getClubId();
		}

		// TODO: thumbnail file 검증 코드 or 검증 모듈 필요 (용량제한, 사이즈 제한)
		// TODO: 프론트에서 사이즈 안맞는 이미지 보낼 때, 카톡 프로필처럼 이미지 즉시 편집하는 기능 추가
		if(clubCreateRequestDto.getClubImageFile().getSize() > 0) { // 이미지가 존재할 경우 true
			String uploadDir = "tmp/club/thumbnails/";
			String fileName = StringUtils.cleanPath(
				Objects.requireNonNull(clubCreateRequestDto.getClubImageFile().getOriginalFilename()));

			FileUploadUtil.saveFile(uploadDir, fileName, clubCreateRequestDto.getClubImageFile());
		}

		// TODO: AWS S3 와 연계하여 저장하는 코드 추가 필요
		/*...*/

		return null;
	}
	@Override
	public boolean checkClubNameDuplication(String clubName) throws DuplicatedClubNameException {
		// 클럽 이름이 이미 존재하면 true
		// 아니면 false
		Club club = clubRepository.findByClubName(clubName);

		if(club == null) {
			return true;
		}

		throw new DuplicatedClubNameException("DuplicatedClubNameException called");
	}
	// [공통] 클럽 리스트 조회
	@Override
	public List<Club> getClubList(){
		return clubRepository.findAll();
	}
	// [공통] 특정 클럽 상세조회 (이미지, 배너, 소개)
	@Override
	public Club getClubDetail(Long clubId){
		return clubRepository.getById(clubId);
	}
	// [공통] 그룹 입장 요청 (클럽에 등록된 유저인지 확인)
	@Override
	public void enterClub(Long clubId, String password){
		Club club = clubRepository.getById(clubId);

		if(/*club.getMaximumUserCount() < "현재 클럽 인원수(JPA)" + 1 */false){
			// 이미 가득찬 클럽입니다.
		}
		else {
			if(!club.getPassword().isEmpty()){ // 패스워드가 있다면
				if(club.getPassword().equals(password)) { //패스워드 검사 후 입장
					//clubRepository.addMember();
				}
			}
		}
	}

	// [관리자 - 클럽 관리] - 클럽 정보 변경 (메인이미지, 이름, 소개글 <--(일반)--구분--(민감)--> 인원제한, 비공개 전환, 비밀번호 변경)
	// getMaster 부분 너무 별로임... 고쳐야됨
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Club updateClubInfo(Long clubId, ClubUpdateRequestDto clubUpdateRequestDto){
		String master = clubRepository.getById(clubId).getMaster();
		Club club = clubUpdateRequestDto.toEntity();

		club.setClubId(clubId);
		club.setMaster(master);
		return clubRepository.save(club);
	}
	// [관리자 - 클럽 관리] - 클럽장 권한 위임
	// Thread Local 에서 Auth 정보 가져오기.
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delegateClubMasterPrivileges(Long clubId, String delegatedUsername) throws AuthenticationException{
		Club club = clubRepository.getById(clubId);

		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		// 나중에 스프링 시큐리티에서 검사? 방법은 찾아봐야 할 듯
		// 검사 - 인증여부(Anonymous), 인가여부(Authorization, HasRole)
		if("인증이 된 유저인가?".isEmpty()){}
		if("API를 호출한 유저가 클럽관리자인가?".equals(club.getMaster()) || true){
			if("권한을 위임해줄 유저명은 유효한 정보인가?".isEmpty()){}
			club.setMaster(delegatedUsername);
			clubRepository.save(club);
			return true;
		}

		return false;
	}
	// [관리자 - 클럽 관리] - 클럽 삭제
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteClub(Long clubId){
		clubRepository.deleteById(clubId);
	}

	// [클럽 멤버 - 일반] - 채널 조회
	@Override public void getClubChannelList(){}
	// [클럽 멤버 - 일반] - 클럽 탈퇴
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void withdrawClubMembership(){}

	// [관리자 - 멤버 관리] - 멤버 초대
	@Override public void inviteClubMember(){}
	// [관리자 - 멤버 관리] - 그룹 가입 요청 승인
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void approveClubMembershipRequest(){}
	// [관리자 - 멤버 관리] - 멤버 권한 승격(변경으로 바꿔 grant
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void grantClubMemberPrivileges(){}
	// [관리자 - 멤버 관리] - 멤버 추방
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void kickClubMember(){}

	// [관리자 - 채널 관리(나중에)] - 채널 생성
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createClubChannel(){}
	// [관리자 - 채널 관리(나중에)] - 채널 수정
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateClubChannelInfo(){}
	// [관리자 - 채널 관리(나중에)] - 채널 삭제
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteClubChannel(){}

	// [클럽 멤버 - 채널 글 관련(가장 마지막에 작업하기)] - 글 작성 (+ 비밀번호 or 보기권한 설정 가능)
	@Override
	public void addClubPost(){}
	// [클럽 멤버 - 채널 글 관련(가장 마지막에 작업하기)] - 글 목록 불러오기
	@Override
	public void getClubPostList(){}
	// [클럽 멤버 - 채널 글 관련(가장 마지막에 작업하기)] - 글 내용 불러오기
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void getClubPostDetail(){}
	// [클럽 멤버 - 채널 글 관련(가장 마지막에 작업하기)] - 글 수정
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateClubPost(){}
	// [클럽 멤버 - 채널 글 관련(가장 마지막에 작업하기)] - 글 삭제
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeClubPost(){}
}