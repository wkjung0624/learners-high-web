package com.mightyotter.learnershigh.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// import com.mightyotter.learnershigh.domain.model.Member; // Member 클래스를 Model로 분리 보관 해야할까?

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query(value = "SELECT EMAIL FROM TBL_MEMBER WHERE EMAIL = :email LIMIT 1")
	String findByEmail(@Param("email") String email);
}
