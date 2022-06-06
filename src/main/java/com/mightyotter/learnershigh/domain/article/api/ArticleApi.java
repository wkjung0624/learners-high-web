package com.mightyotter.learnershigh.domain.article.api;

import com.mightyotter.learnershigh.domain.article.application.ArticleService;
import com.mightyotter.learnershigh.domain.article.dao.Article;
import com.mightyotter.learnershigh.domain.article.dto.ArticleSaveDto;
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
	public void createArticle(HttpServletRequest request, @RequestBody ArticleSaveDto articleSaveDto) {

		HttpSession session =  request.getSession();

		articleService.save(
			articleSaveDto.getCategoryId(),
			session.getAttribute("userId").toString(),
			articleSaveDto.getTitle(),
			articleSaveDto.getContent(),
			articleSaveDto.getHashtag(),
			articleSaveDto.getThumbnailUrlPath(),
			articleSaveDto.getMediaDataJson()
		);
	}
//
//	/**
//	 * 글 목록 조회
//	 */
	@GetMapping("/article")
	public ResponseEntity<Page<Article>> getArticles(/*@PageableDefault(size=10, sort="")*/Pageable page) {
		Page<Article> articlePage = articleService.getArticleList(page);
		return new ResponseEntity<>(articlePage, HttpStatus.OK);
	}

	/**
	 * 특정 글 내용 가져오기
	 */
	@GetMapping("/article/{id}")
	public Article getArticleContent(@PathVariable Long id) {
		return articleService.getArticle(id);
	}

	/**
	 * 특정 글 수정
	 */
	@PostMapping("/article/{id}/update")
	public void updateArticle() {/*..*/}

	/**
	 * 작성한 글 삭제
	 */
	@PostMapping("/article/{id}/delete")
	public void deleteArticle() {/*...*/}


	/**
	 * 댓글 입력
	 */
	@PostMapping("/comment/{id}")
	public void createComment() {
		/*
		Body {
			Comment : “새로 작성하는 댓글”,
		}
		* */
	}

	/**
	 * 댓글 목록 조회
	 */
	@GetMapping("/comment/{id}")
	public void getArticleComment() {/*...*/}

	/**
	 * 댓글 수정
	 */
	@PostMapping("/comment/{id}/update")
	public void updateArticleComment() {
		/*
		Body {
			commentId : “234”,
			comment  : “이것은 수정된 댓글입니다”,
		}
		*/
	}

	/**
	 * 댓글 삭제
	 */
	@GetMapping("/comment/{id}/delete")
	public void deleteComment() {
		/*
		Body {
			commentId : “234”    <— 댓글 고유 번호
		}
		*/
	}
}


//	/** 동영상 업로드 (이건 어떻게 하는거지 ?)*/
//	@PostMapping("/article/{id}")
//	- 동영상 파일 조회
//	- GET /v1/article/{id}?_s=a109f537e0c9d2…
//	- 동영상 삭제 ( 삭제가 필요할까? 설령 삭제한다면 보이지 않게만 처리하고 따로 보관? )
//	- 동영상 수정 ( 사실 수정이라는게 필요할까 ? )