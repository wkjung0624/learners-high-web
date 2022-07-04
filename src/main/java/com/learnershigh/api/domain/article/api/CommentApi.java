package com.learnershigh.api.domain.article.api;

import com.learnershigh.api.domain.article.domain.Comment;
import com.learnershigh.api.domain.article.dto.CommentSaveDto;
import com.learnershigh.api.global.common.response.StandardResponseBody;
import com.learnershigh.api.global.common.response.data.DataLayer;
import com.learnershigh.api.domain.article.application.CommentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CommentApi {
	private final CommentService commentService;
	@PostMapping("/article/{articleId}/comment")
	public ResponseEntity<StandardResponseBody> createComment(@PathVariable Long articleId, @RequestBody CommentSaveDto commentSaveDto){
		commentService.createComment(articleId,commentSaveDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(
				StandardResponseBody.builder()
					.data(DataLayer.builder()
						.status(true)
						.build())
					.build());
	}
	@GetMapping("/article/{articleId}/comment")
	public ResponseEntity<StandardResponseBody> getCommentList(@PathVariable Long articleId){
		List<Comment> commentList = commentService.getComments(articleId);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				StandardResponseBody.builder()
					.data(DataLayer.builder()
						.items(commentList)
						.build())
					.build());
	}
	// 수정과 삭제는 CommentId 만 있으면 되는데 일관된 주소를 쓰기 위해서 굳이 ArticleId 를 써야할까?
	// TODO: true 로 공통된 결과를 출력하는 것은 하나만 있어도 되는거 아닐까? 너무 반복됨
	@PostMapping("/article/{articleId}/comment/{commentId}/edit")
	public ResponseEntity<StandardResponseBody> editComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody String content){
		commentService.editComment(commentId, content);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				StandardResponseBody.builder()
					.data(DataLayer.builder()
						.status(true)
						.build())
					.build());
	}
	// 수정과 삭제는 CommentId 만 있으면 되는데 일관된 주소를 쓰기 위해서 굳이 ArticleId 를 써야할까?
	@PostMapping("/article/{articleId}/comment/{commentId}/delete")
	public ResponseEntity<StandardResponseBody> deleteComment(@PathVariable Long articleId, @PathVariable Long commentId){
		commentService.deleteComment(commentId);

		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(
				StandardResponseBody.builder()
					.data(DataLayer.builder()
						.status(true)
						.build())
					.build());
	}
}
