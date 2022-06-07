package com.mightyotter.learnershigh.domain.article.dto;

import com.mightyotter.learnershigh.domain.article.dao.Article;
import com.mightyotter.learnershigh.domain.article.dao.Comment;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentEditDto {

	private Long commentId;
	private String content;
	public Comment toEntity(){
		return Comment.builder()
			.commentId(commentId)
			.content(content)
			.build();
	}
}
