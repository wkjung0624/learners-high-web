package com.mightyotter.learnershigh.domain.member.dao;

import com.mightyotter.learnershigh.domain.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import com.mightyotter.learnershigh.domain.model.Member; // Member 클래스를 Model로 분리 보관 해야할까?

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findOneByEmail(String email);
	Optional<Member> findOneByUsername(String username);
	void delete(Member member);
}
