package com.mightyotter.learnershigh.domain.article.application;

import com.mightyotter.learnershigh.domain.article.dao.Article;
import com.mightyotter.learnershigh.domain.article.dao.ArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;

	@Transactional(rollbackFor = Exception.class)
	public Long save(Long categoryId, String authorId, String title, String content, String hashtag,
		String thumbnailUrlPath, String mediaDataJson) {
		articleRepository.save(Article.builder()
			.categoryId(categoryId)
			.authorId(authorId)
			.title(title)
			.content(content)
			.hashtag(hashtag)
			.thumbnailUrlPath(thumbnailUrlPath)
			.mediaDataJson(mediaDataJson)
			.build());
		return 1L;
	}
//	public List<Article> getArticleList(Pageable page){
//		return articleRepository.findAllOrderByArticleId(page);
//	}
//
//	public Article getArticle(Long id){
//		return articleRepository.findById(id).orElse(null);
//	}
}
