package com.mightyotter.learnershigh.domain.club.dao;

import com.mightyotter.learnershigh.domain.club.domain.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

}
