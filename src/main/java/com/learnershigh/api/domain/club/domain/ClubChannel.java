package com.learnershigh.api.domain.club.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClubChannel {
	@Id @Column(name = "CLUB_CHANNEL_ID")
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Club club;

	@ManyToOne
	private Channel channel;
}
