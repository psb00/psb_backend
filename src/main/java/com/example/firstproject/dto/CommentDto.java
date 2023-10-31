package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드에 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수 없는 생성자
@Getter // 각 필드값을 조회할 수 있는 getter메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 메서드 생성
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(comment.getId(),comment.getArticle().getId(),
                comment.getNickname(),comment.getBody());
    }
}
