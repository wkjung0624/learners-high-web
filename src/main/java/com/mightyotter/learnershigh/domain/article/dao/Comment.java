package com.mightyotter.learnershigh.domain.article.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mightyotter.learnershigh.global.common.entity.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "TBL_COMMENT")
public class Comment extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	// 참조하는 객체를 기준으로 find 할 때 sendError() 발생시 해결방법 : https://cupeanimus.tistory.com/57
	@JsonIgnore
	@ManyToOne
	//@JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID")
	private Article article;

	@Column
	private String writer;
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	// 대댓글 관련 1 (아직 미구현)
	@Column
	private int depth;
	// 대댓글 관련 2 (아직 미구현)
	@Column
	private Long referenceId;
}
