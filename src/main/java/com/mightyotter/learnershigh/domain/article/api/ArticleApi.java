package com.mightyotter.learnershigh.domain.article.api;

import com.mightyotter.learnershigh.domain.article.application.ArticleService;
import com.mightyotter.learnershigh.domain.article.domain.Article;
import com.mightyotter.learnershigh.domain.article.dto.ArticleSaveDto;
import com.mightyotter.learnershigh.domain.article.dto.ArticleUpdateDto;
import com.mightyotter.learnershigh.global.common.response.StandardResponseBody;
import com.mightyotter.learnershigh.global.common.response.data.DataLayer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ArticleApi {

	// User 파트에서 'Create' 와 'Delete' 를 담당
	// private final 인 이유는?
	private final ArticleService articleService;

	/**
	 * 게시글 작성
	 */
	@PostMapping("/article")
	public ResponseEntity<StandardResponseBody> createArticle(HttpServletRequest request, @RequestBody ArticleSaveDto articleSaveDto) {
		HttpSession session =  request.getSession();
		articleSaveDto.setAuthorId(session.getAttribute("username").toString());

		articleService.save(articleSaveDto);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(
				StandardResponseBody.builder()
					.data(
						DataLayer.builder()
							.items(articleSaveDto)
							.build()
					).build()
			);
	}

	/**
	 * 글 목록 조회
	 */
	@GetMapping("/article")
	public ResponseEntity<Page<Article>> getArticles(/*@PageableDefault(size=10, sort="")*/Pageable page) {
		Page<Article> articlePage = articleService.getArticleList(page);
		return new ResponseEntity<>(articlePage, HttpStatus.OK);
	}

	/**
	 * 특정 글 내용 가져오기
	 */
	@GetMapping("/article/{id}")
	public ResponseEntity<StandardResponseBody> getArticleContent(@PathVariable Long id) {
		// 예외 처리 필요 - 권한확인불가, 인증확인불가, 아티클확인불가 등
		Article article = articleService.getArticle(id);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				StandardResponseBody.builder()
					.data(
						DataLayer.builder()
							.items(article)
							.build())
					.build()
			);
	}

	/**
	 * 특정 글 수정
	 */
	@PostMapping("/article/{id}/update")
	public ResponseEntity<StandardResponseBody> updateArticle(@PathVariable Long id, @RequestBody ArticleUpdateDto articleUpdateDto) {
		// 예외 처리 필요 - 권한확인불가, 인증확인불가, 아티클확인불가 등
		articleService.updateArticle(id, articleUpdateDto);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(
				StandardResponseBody.builder()
					.data(
						DataLayer.builder()
							.status(true)
							.build())
					.build()
			);
	}

	/**
	 * 작성한 글 삭제
	 */
	@PostMapping("/article/{id}/delete")
	public ResponseEntity<StandardResponseBody> deleteArticle(@PathVariable Long id) {
		// 예외 처리 필요 - 권한확인불가, 인증확인불가, 아티클확인불가 등
		articleService.deleteArticle(id);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(
				StandardResponseBody.builder()
					.data(
						DataLayer.builder()
							.status(true)
							.build())
					.build()
			);
		//
	}
}


//	/** 동영상 업로드 (이건 어떻게 하는거지 ?)*/
//	@PostMapping("/article/{id}")
//	- 동영상 파일 조회
//	- GET /v1/article/{id}?_s=a109f537e0c9d2…
//	- 동영상 삭제 ( 삭제가 필요할까? 설령 삭제한다면 보이지 않게만 처리하고 따로 보관? )
//	- 동영상 수정 ( 사실 수정이라는게 필요할까 ? )