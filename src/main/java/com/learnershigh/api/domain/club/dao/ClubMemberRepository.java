package com.learnershigh.api.domain.club.dao;

import com.learnershigh.api.domain.club.domain.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

}
