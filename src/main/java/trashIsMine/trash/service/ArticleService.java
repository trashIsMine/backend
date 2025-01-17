package trashIsMine.trash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trashIsMine.trash.domain.Article;
import trashIsMine.trash.repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getArticlesByUsername(String username) {
        // 데이터베이스에서 사용자 이름으로 글 조회
        return articleRepository.findByUsername(username);
    }

}

