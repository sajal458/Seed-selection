package CodeCrafter.example.Seed_Selection.service;

import CodeCrafter.example.Seed_Selection.entity.Article;
import java.util.List;

public interface Articleservice {
    List<Article> getAllArticles();
    Article saveArticle(Article article);
    Article getArticleById(int id);
    void deleteArticleById(int id);
}