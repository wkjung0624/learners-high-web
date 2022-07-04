package com.learnershigh.api.domain.article.dto;

import com.learnershigh.api.domain.article.domain.Comment;
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
