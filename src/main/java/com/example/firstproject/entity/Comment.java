package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor // 모든 필드의 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 없는 기본 생성자 자동 생성
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 id값 자동으로 1씩 증가
    private Long id;


    @Column
    private String nickname;
    @Column
    private String body;

    @ManyToOne //Commnet와 article의 다대일 관계 설정해주는 어노테이션
    @JoinColumn(name="article_id") // article의 id값을 외래키로 지정해주는 어노테이션
    private Article article;

    public static Comment createComment(CommentDto dto, Article article) {
        //의도적으로 예외 발생
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getArticleId() != article.getId())
            throw  new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        //엔티티 생성 및 반환
        return new Comment(dto.getId(), dto.getNickname(),dto.getBody(),article);
    }

    public void patch(CommentDto dto) {
        //예외발생

        //1. json의 id와 url의 id가 다른 경우
        if(this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패 ! 잘못된 id가 입력됐습니다.");
        }
        //객체 갱신
        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null){
            this.body = dto.getBody();
        }

    }
}
