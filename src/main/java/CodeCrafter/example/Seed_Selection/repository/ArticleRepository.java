package CodeCrafter.example.Seed_Selection.repository;

import CodeCrafter.example.Seed_Selection.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
