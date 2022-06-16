package com.mightyotter.learnershigh.domain.club.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
	Club findByClubName(String clubName);
}
