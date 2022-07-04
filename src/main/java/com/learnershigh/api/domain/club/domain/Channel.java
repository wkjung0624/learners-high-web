package com.learnershigh.api.domain.club.domain;

import com.learnershigh.api.domain.club.model.PermissionLevel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Channel {
	@Id @Column(name = "CHANNEL_ID")
	@GeneratedValue
	private Long id;

	private String name;

	private PermissionLevel permissionLevel;

	public static Channel createDefaultChannel(){
		return Channel.builder()
			.name("Welcome")
			.permissionLevel(PermissionLevel.MEMBER)
			.build();
	}
}
