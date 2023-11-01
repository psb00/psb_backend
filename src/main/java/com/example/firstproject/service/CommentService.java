package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // 여기서 articleRepository를 가져온 이유는 게시글이 있어야 댓글을 생성할 때 게시글의 존재 여부를 알 수 있어서.




     public  List<CommentDto> comments(Long articleId) {

        /*//1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        */
        //3. 결과 반환
        return commentRepository.findByArticleId(articleId).stream().map(comment->CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
        //댓글 엔티티 목록을 조회 해서 스트림으로 전환 후 map을 사용해 entity를 dto로 맵핑 후 스트림을 리스트로 변환
    }

    @Transactional //DB를 수정하는 것이기 때문에 예외 발생 시 롤백 해주는 어노테이션
    public CommentDto create(Long articleId, CommentDto dto) {
        //1.게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " +
                        "대상 게시글이 없습니다."));

        //2.댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);

        //3.댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        //4.DTO로 변환해 저장

        return CommentDto.createCommentDto(created);

    }

    @Transactional
    public CommentDto update(Long id,CommentDto dto) {
         //댓글조회 예외발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" + " 대상 댓글이 없습니다!"));
         // 댓글 수정
        target.patch(dto);

        //DB갱신

        Comment updated = commentRepository.save(target);


        //댓글 엔티티를 DTO로 변환 및 반환
         return CommentDto.createCommentDto(updated);

    }

    public CommentDto delete(Long id) {
         //댓글조회 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패!" + "삭제할 대상이 없습니다."));
        //댓글삭제
        commentRepository.delete(target);
        //삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
