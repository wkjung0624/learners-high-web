package com.learnershigh.api.domain.member.domain;

import com.learnershigh.api.domain.model.BaseTimeEntity;
import com.learnershigh.api.domain.model.Role;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Builder // https://devfunny.tistory.com/337
@AllArgsConstructor
@Getter
@Setter
//@Data //(@Getter 와 @Setter 를 합친 것 )
@Entity
public class Member extends BaseTimeEntity implements Serializable {
	@Id	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Length(min = 6, max = 30)
	@Column(length = 30, unique = true, nullable = false)
	private String username;

	@Length(min = 64, max = 64)
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String nickname;

	@Column(unique = true, nullable = false)
	private String email;

	// JPA 에서는 SQL 의 Trigger 기능을 어떻게 구현하나?
	// 이메일으 변경할 시 'verifiedEmail' 의 값은 false 로 강제되야함
	@Column(nullable = false)
	private boolean verifiedEmail;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
}