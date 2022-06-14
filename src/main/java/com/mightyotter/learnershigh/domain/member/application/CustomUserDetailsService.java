package com.mightyotter.learnershigh.domain.member.application;

import com.mightyotter.learnershigh.domain.member.dao.Member;
import com.mightyotter.learnershigh.domain.member.dao.MemberRepository;
import com.mightyotter.learnershigh.global.config.auth.MemberContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member member = memberRepository.findOneByUsername(username).orElse(null);

		if (member == null){
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}

		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(member.getRole().getKey()));

		return new MemberContext(member, roles);
	}
}
