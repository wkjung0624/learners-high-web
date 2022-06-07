package com.mightyotter.learnershigh.domain.article.application;

import com.mightyotter.learnershigh.domain.article.dao.Article;
import com.mightyotter.learnershigh.domain.article.dao.ArticleRepository;
import com.mightyotter.learnershigh.domain.article.dto.ArticleSaveDto;
import com.mightyotter.learnershigh.domain.article.dto.ArticleUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;

	@Transactional(rollbackFor = Exception.class)
	public Article save(@RequestBody ArticleSaveDto articleSaveDto) {
		return articleRepository.save(articleSaveDto.toEntity());
	}
	public Page<Article> getArticleList(Pageable page) {
		return articleRepository.findAll(page);
	}
	public Article getArticle(Long id){
		return articleRepository.findByArticleId(id).orElse(null);
	}

	public void updateArticle(Long id, ArticleUpdateDto articleUpdateDto){
		Article article = articleRepository.findByArticleId(id).orElse(null);

		if(article != null) {
			article.setCategoryId(articleUpdateDto.getCategoryId());
			article.setHashtag(articleUpdateDto.getHashtag());
			article.setTitle(articleUpdateDto.getTitle());
			article.setContent(articleUpdateDto.getContent());
			article.setMediaDataJson(articleUpdateDto.getMediaDataJson());
			article.setIsVisible(articleUpdateDto.getIsVisible());
			articleRepository.save(article);
		}
	}

	public void deleteArticle(Long id){
		articleRepository.deleteById(id);
	}
}
