package com.learnershigh.api.domain.club.dao;

import com.learnershigh.api.domain.club.domain.Club;
import com.learnershigh.api.domain.club.domain.ClubChannel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubChannelRepository extends JpaRepository<ClubChannel, Long> {
	List<ClubChannel> findAllByClub(Club club);
}
