package com.example.firstproject.entity;

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
}
