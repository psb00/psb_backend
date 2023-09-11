package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // entity -> DTO(data transfer object, 컨트롤러에 담기는 변수를 받아주는 역할)를 DB테이블로 바꿔주는 역할임, DB는 java언어를 모르기 때문에.

public class Article {
    @Id //key값, 데이터 구분 역할
    @GeneratedValue // 대푯값 번호 자동 지정
    private Long id;
    @Column //테이블의 두 필드를 DB에 연결해주는 역할
    private  String title;
    @Column //테이블의 두 필드를 DB에 연결해주는 역할
    private String content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
