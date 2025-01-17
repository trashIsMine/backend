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
import trashIsMine.trash.dto.ArticleWithFileDto;
import trashIsMine.trash.repository.ArticleRepository;
import trashIsMine.trash.repository.UserRepository;
import trashIsMine.trash.util.FileUploadUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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


    private static final String UPLOAD_DIR = "/home/ubuntu/uploads";

    @PostMapping("/articles/create")
    public ResponseEntity<String> createArticle(@ModelAttribute ArticleForm form) {
        log.info(form.toString());
        try {
            String imagePath = null;
            if (form.getImageFile() != null && !form.getImageFile().isEmpty()) {
                imagePath = FileUploadUtil.saveFile(UPLOAD_DIR, form.getImageFile().getOriginalFilename(), form.getImageFile());
            } else {
                log.warn("Image file is null or empty");
            }
            Article article = form.toEntity(imagePath);

            log.info(article.toString());
            Article saved = articleRepository.save(article);
            log.info(saved.toString());

            return ResponseEntity.ok("success " + saved.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "success";
    }

//    @GetMapping("/articles")
//    public ResponseEntity<List<Article>> index() {
//        List<Article> articleEntityList = articleRepository.findAll();
//        return new ResponseEntity<>(articleEntityList, HttpStatus.OK);
//    }


//    @GetMapping("/articles")
//    public ResponseEntity<List<ArticleWithFileDto>> index() {
//        try {
//            List<Article> articles = articleRepository.findAll();
//            List<ArticleWithFileDto> articleDtos = articles.stream()
//                    .map(ArticleWithFileDto::new)
//                    .collect(Collectors.toList());
//            return new ResponseEntity<>(articleDtos, HttpStatus.OK);
//        } catch (Exception e) {
//            // 예외 처리
//            log.error("Error occurred while fetching articles: ", e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleWithFileDto>> index() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleWithFileDto> articleDtos = articles.stream()
                .map(ArticleWithFileDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(articleDtos, HttpStatus.OK);
    }



    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "success";
    }

    @PostMapping("/articles/update")
    public ResponseEntity<String> updateArticle(@ModelAttribute ArticleForm form) {
        try {
            String imagePath = FileUploadUtil.saveFile(UPLOAD_DIR, form.getImageFile().getOriginalFilename(), form.getImageFile());
            Article articleEntity = form.toEntity(imagePath);

            Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
            if (target != null) {
                articleRepository.save(articleEntity);
                return ResponseEntity.ok("success " + articleEntity.getId());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
}

