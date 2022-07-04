package com.learnershigh.api.domain.club.dao;

import com.learnershigh.api.domain.club.domain.Club;
import com.learnershigh.api.domain.club.domain.ClubMember;
import com.learnershigh.api.domain.member.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
	ClubMember findByMember(Member member);
	List<ClubMember> findAllByClub(Club club);
}
