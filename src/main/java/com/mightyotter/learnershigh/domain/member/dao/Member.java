package com.mightyotter.learnershigh.domain.member.dao;

import com.mightyotter.learnershigh.global.common.entity.BaseTimeEntity;
import com.mightyotter.learnershigh.global.config.Role;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder // https://devfunny.tistory.com/337
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Data //(@Getter 와 @Setter 를 합친 것 )
@Entity
@Table(name = "TBL_MEMBER")
public class Member extends BaseTimeEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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