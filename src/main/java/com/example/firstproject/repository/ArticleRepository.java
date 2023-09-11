package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

//crudRepository -> JPA에서 제공하는 인터페이스로 엔티티를 관리(생성,조회,수정,삭제)할 수 있다.

public interface ArticleRepository extends CrudRepository<Article,Long> {


}
