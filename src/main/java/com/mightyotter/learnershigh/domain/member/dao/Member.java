package com.mightyotter.learnershigh.domain.member.dao;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data //(@Getter 와 @Setter 를 합친 것 )
@Entity
@Table(name = "TBL_MEMBER")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Length(min = 6, max = 30)
	@Column(length = 30, nullable = false)
	private String userId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String userPw;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String nickName;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String email;

	@Column(nullable = false)
	private boolean verifiedEmail;

	@Temporal(TemporalType.TIMESTAMP)
	private Date accountDeletionRequestDate;

	@Builder
	public Member(String userId, String userPw, String nickName, String email, Boolean verifiedEmail){
		this.userId = userId;
		this.userPw = userPw;
		this.email = email;
		this.nickName = nickName;
		this.verifiedEmail = verifiedEmail;
	}
}
