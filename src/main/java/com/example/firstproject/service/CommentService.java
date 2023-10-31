package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // 여기서 articleRepository를 가져온 이유는 게시글이 있어야 댓글을 생성할 때 게시글의 존재 여부를 알 수 있어서.




     public  List<CommentDto> comments(Long articleId) {

        //1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }

        //3. 결과 반환
        return dtos;
    }

}
