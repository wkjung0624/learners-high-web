package com.mightyotter.learnershigh.domain.article.dto;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
}
