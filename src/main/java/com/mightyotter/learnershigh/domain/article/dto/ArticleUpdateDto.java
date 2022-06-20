package com.mightyotter.learnershigh.domain.article.dto;

import com.mightyotter.learnershigh.domain.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ArticleUpdateDto {
	private Long categoryId;
	private String title;
	private String content;
	private String hashtag;
	private String thumbnailUrlPath;
	private String mediaDataJson;
	private short isVisible;

	public Article toEntity(){
		return Article.builder()
			.categoryId(categoryId)
			.title(title)
			.content(content)
			.hashtag(hashtag)
			.thumbnailUrlPath(thumbnailUrlPath)
			.mediaDataJson(mediaDataJson)
			.isVisible(isVisible)
			.build();
	}
}
