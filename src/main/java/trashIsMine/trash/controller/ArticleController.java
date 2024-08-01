package trashIsMine.trash.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import trashIsMine.trash.domain.User;
import trashIsMine.trash.dto.ArticleForm;
import trashIsMine.trash.domain.Article;
import trashIsMine.trash.repository.ArticleRepository;
import trashIsMine.trash.repository.UserRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/index")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

//    public ArticleController(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }
    @Autowired
    private UserRepository userRepository; // 이 부분 확인

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";

    }

    @PostMapping("/articles/create")
    public String createArticle(@RequestBody ArticleForm form){
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        // 2. 현재 로그인한 사용자의 User 엔티티를 가져옵니다.

        log.info(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "success" + saved.getId();
    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기.
        return "success";
    }

//    @GetMapping("/articles")
//    public String index(Model model){
//        //1.모든 데이터 가져오기
//        List<Article> articleEntityList = articleRepository.findAll();
//        //2.모델에 데이터 등록하기
//        model.addAttribute("articleList", articleEntityList);
//        //3.뷰 페이지 설정하기
//        return "success";
//    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> index() {
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 데이터와 HTTP 상태 코드 200 OK를 반환
        return new ResponseEntity<>(articleEntityList, HttpStatus.OK);
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정하기.
        return "success";
    }


    @PostMapping("/articles/update")
    public String update(@RequestBody ArticleForm form){
        //1.DTO를 엔티티로 변환하기

        Article articleEntity;
        articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2.엔티티를 DB에 저장하기
        //2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2. 기존 데이터 값을 갱신하기
        if(target != null) {
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }

        return "success"+ articleEntity.getId();
    }


//    @ApiOperation(value = "Get an article by ID")
//    @GetMapping("/articles/{id}")
//    public String show(
//            @ApiParam(value = "ID of the article to retrieve", required = true, example = "1")
//            @PathVariable Long id, Model model) {
//        log.info("id = " + id);
//        Article articleEntity = articleRepository.findById(id).orElse(null);
//        model.addAttribute("article", articleEntity);
//        return "articles/show";
//    }

}
