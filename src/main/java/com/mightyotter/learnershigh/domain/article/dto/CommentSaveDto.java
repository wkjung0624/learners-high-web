package com.mightyotter.learnershigh.domain.article.dto;

import com.mightyotter.learnershigh.domain.article.domain.Comment;

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
