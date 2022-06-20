package com.mightyotter.learnershigh.domain.article.dao;

import com.mightyotter.learnershigh.domain.article.domain.Article;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	//List<Article> findOrderByArticleId(Pageable page);
	Optional<Article> findByArticleId(Long id);
	Page<Article> findAll(Pageable pageable);
}
