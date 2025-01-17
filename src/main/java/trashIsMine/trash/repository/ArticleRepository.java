package trashIsMine.trash.repository;

import org.springframework.data.repository.CrudRepository;
import trashIsMine.trash.entity.Article;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
    List<Article> findByUsername(String username);
}
