package com.mightyotter.learnershigh.domain.article.api;

import com.mightyotter.learnershigh.domain.article.application.CommentService;
import com.mightyotter.learnershigh.domain.article.dao.Comment;
import com.mightyotter.learnershigh.domain.article.dto.CommentSaveDto;
import java.util.List;
import lombok.AllArgsConstructor;
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
	public void createComment(@PathVariable Long articleId, @RequestBody CommentSaveDto commentSaveDto){
		commentService.createComment(articleId,commentSaveDto);
	}
	@GetMapping("/article/{articleId}/comment")
	public List<Comment> getComments(@PathVariable Long articleId){
		return commentService.getComments(articleId);
	}
	// 수정과 삭제는 CommentId 만 있으면 되는데 일관된 주소를 쓰기 위해서 굳이 ArticleId 를 써야할까?
	@PostMapping("/article/{articleId}/comment/{commentId}/edit")
	public Comment editComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody String content){
		return commentService.editComment(commentId, content);
	}
	// 수정과 삭제는 CommentId 만 있으면 되는데 일관된 주소를 쓰기 위해서 굳이 ArticleId 를 써야할까?
	@PostMapping("/article/{articleId}/comment/{commentId}/delete")
	public void deleteComment(@PathVariable Long articleId, @PathVariable Long commentId){
		commentService.deleteComment(commentId);
	}
}
