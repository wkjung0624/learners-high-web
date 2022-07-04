package com.learnershigh.api.domain.article.dto;

import com.learnershigh.api.domain.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ArticleSaveDto {
	private Long categoryId;
	private String authorId;
	private String title;
	private String content;
	private String hashtag;
	private String thumbnailUrlPath;
	private String mediaDataJson;

	public Article toEntity(){
		return Article.builder()
			.categoryId(categoryId)
			.authorId(authorId)
			.title(title)
			.content(content)
			.hashtag(hashtag)
			.thumbnailUrlPath(thumbnailUrlPath)
			.mediaDataJson(mediaDataJson)
			.build();
	}
}
