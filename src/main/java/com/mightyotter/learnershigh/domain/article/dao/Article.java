package com.mightyotter.learnershigh.domain.article.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {

	// 게시글 고유 ID
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long articleId;

	// 카테고리 ID
	@Column
	private Long categoryId;

	// 작성자 ID
	@Column(columnDefinition = "TEXT", nullable = false)
	private String authorId;

	// 글 제목
	@Column(columnDefinition = "TEXT", nullable = false)
	private String title;

	// 글 내용
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	// 해시태그 (JSON), JPA 로도 리스트가 가능할까?
	@Column(nullable = false)
	private String hashtag;

	// 썸네일 url
	@Column(columnDefinition = "TEXT", nullable = false)
	private String thumbnailUrlPath;

	// 외부 데이터[영상,사진] 링크(JSON), JSON으로 한 이유는 단순히 하나의 링크만 가져오지 않을수 있으니까
	@Column(columnDefinition = "TEXT")
	private String mediaDataJson;
}
