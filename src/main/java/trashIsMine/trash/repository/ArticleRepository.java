package trashIsMine.trash.repository;

import org.springframework.data.repository.CrudRepository;
import trashIsMine.trash.domain.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
