package com.mightyotter.learnershigh.domain.article.application;

import com.mightyotter.learnershigh.domain.article.dao.Article;
import com.mightyotter.learnershigh.domain.article.dao.ArticleRepository;
import com.mightyotter.learnershigh.domain.article.dao.Comment;
import com.mightyotter.learnershigh.domain.article.dao.CommentRepository;
import com.mightyotter.learnershigh.domain.article.dto.CommentEditDto;
import com.mightyotter.learnershigh.domain.article.dto.CommentSaveDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
