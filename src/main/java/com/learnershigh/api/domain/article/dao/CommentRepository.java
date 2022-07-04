package com.learnershigh.api.domain.article.dao;

import com.learnershigh.api.domain.article.domain.Article;
import com.learnershigh.api.domain.article.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	// FK 로 조회, https://arahansa.github.io/docs_spring/jpa.html#repositories
	// @Query 에서 Parameter Binding 하는 방법 : https://eclipse4j.tistory.com/325
	//	@Query("SELECT * FROM TBL_COMMENT WHERE ARTICLE_ID = :articleId")
	//	List<Comment> findByArticleId(@Param("articleId") Long articleId);
	List<Comment> findByArticle(Article article);
}
