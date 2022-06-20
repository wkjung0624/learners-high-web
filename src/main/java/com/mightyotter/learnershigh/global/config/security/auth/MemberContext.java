package com.mightyotter.learnershigh.global.config.security.auth;


import com.mightyotter.learnershigh.domain.member.domain.Member;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberContext extends User {

	private final Member account;
	public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
		super(member.getUsername(), member.getPassword(), authorities);
		this.account = member;
	}

	public Member getAccount() {
		return account;
	}
}
