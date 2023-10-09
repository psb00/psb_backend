package com.example.firstproject.controller;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // print문으로 확인하는 것이 서버 로그로 정보를 확인하는 어노테이션
public class ArticleController {
    @Autowired //자동 주입
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
      //  System.out.println(form.toString());
        log.info(form.toString());

        Article article = form.toEntity(); // DTO를 Entity로 변환

        //System.out.println(article.toString());
        log.info(article.toString());
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")  // @PathVariable은 url 요청으로 온 전달값의 컨트롤러의 매개변수로 가져오는 어노테이션이다.
    public String show(@PathVariable Long id, Model model) { // 매개변수로 id 받아 오기
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그 찍기
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){ //리파지토리에서 가져온 list를 모델에 등록해야함
        //1.모든 데이터 가져오기
        List<Article> articleEntityList = (List<Article>) articleRepository.findAll(); //여기서 List<Article>은 다운캐스팅 한 것임.
        //2.모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지 설정하기

        return "articles/index";

    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id ,Model model){

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);


        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        Article articleEntity = form.toEntity();

        log.info(articleEntity.toString());

        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        if(target != null){
            articleRepository.save(articleEntity);
        }


        return "redirect:/articles/" + articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete") //http는 get과 post밖에 지원 안함. 원래대로면 DeleteMapping써도 됨
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Article target = articleRepository.findById(id).orElse(null);

        if(target != null){
            articleRepository.delete(target);
            redirectAttributes.addFlashAttribute("msg","삭제됐습니다!");
        }


        return "redirect:/articles";
    }



}
