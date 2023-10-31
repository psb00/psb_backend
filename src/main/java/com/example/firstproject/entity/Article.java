package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // entity -> DTO(data transfer object, 컨트롤러에 담기는 변수를 받아주는 역할)를 DB테이블로 바꿔주는 역할임, DB는 java언어를 모르기 때문에.
@AllArgsConstructor
@NoArgsConstructor // 매개변수 없는 기본 생성자임.. 상황에 따라서 써도 되고 안써도 됨.
@ToString
public class Article {
    @Id //key값, 데이터 구분 역할
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 대푯값 번호 자동 지정
    private Long id;
    @Column //테이블의 두 필드를 DB에 연결해주는 역할
    private  String title;
    @Column //테이블의 두 필드를 DB에 연결해주는 역할
    private String content;


    public Long getId() {
        return id;
    }

    public void patch(Article article) {
        if(article.title != null){
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
}
