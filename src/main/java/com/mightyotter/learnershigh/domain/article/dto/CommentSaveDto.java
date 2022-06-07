package com.mightyotter.learnershigh.domain.article.dto;

import com.mightyotter.learnershigh.domain.article.dao.Article;
import com.mightyotter.learnershigh.domain.article.dao.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSaveDto {

	private String writer;
	private String content;

	public Comment toEntity(){
		return Comment.builder()
			.writer(writer)
			.content(content)
			.build();
	}
}
