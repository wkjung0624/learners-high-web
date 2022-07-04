package com.learnershigh.api.domain.article.application;

import com.learnershigh.api.domain.article.dao.ArticleRepository;
import com.learnershigh.api.domain.article.dao.CommentRepository;
import com.learnershigh.api.domain.article.domain.Article;
import com.learnershigh.api.domain.article.domain.Comment;
import com.learnershigh.api.domain.article.dto.CommentSaveDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
	// JPA 에서 FK 로 연관된 객체를 조회할 때 .getOne() [지금은 getById()]: https://jgrammer.tistory.com/147
	public final ArticleRepository articleRepository;
	public final CommentRepository commentRepository;

	public void createComment(Long articleId, CommentSaveDto commentDto){
		Article article = articleRepository.getById(articleId);

		Comment comment = Comment.builder()
			.article(article)
			.content(commentDto.getContent())
			.writer(commentDto.getWriter())
			.build();

		article.addComment(comment);
		commentRepository.save(comment);
	}

	// pageable 은 나중에
	public List<Comment> getComments(Long articleId){
		Article article = articleRepository.getById(articleId);
		return commentRepository.findByArticle(article);
	}

	public Comment editComment(Long commentId, String content){
		Comment comment = commentRepository.getById(commentId);
		comment.setContent(content);

		return commentRepository.save(comment);
	}

	public void deleteComment(Long commentId){
		commentRepository.deleteById(commentId);
	}
}
