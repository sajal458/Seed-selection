package CodeCrafter.example.Seed_Selection.service;

import CodeCrafter.example.Seed_Selection.entity.Article;
import CodeCrafter.example.Seed_Selection.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements Articleservice {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleById(int id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteArticleById(int id) {
        articleRepository.deleteById(id);
    }
}