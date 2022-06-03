package com.mightyotter.learnershigh.domain.article.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	//List<Article> findAllOrderByArticleId(Pageable page);
	//Optional<Article> findById(Long id);
}
