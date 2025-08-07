package CodeCrafter.example.Seed_Selection.controller;

import CodeCrafter.example.Seed_Selection.entity.Article;
import CodeCrafter.example.Seed_Selection.service.Articleservice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ArticleController {

    private final Articleservice articleService;

    @Value("${upload.dir}")
    private String uploadDir;

    public ArticleController(Articleservice articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles";
    }

    @GetMapping("/articles/new")
    public String createArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "create_article";
    }

    @PostMapping("/articles")
    public String saveArticle(@ModelAttribute Article article, @RequestParam("imageFile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + "/" + fileName));
            article.setImagePath(fileName);
        }
        articleService.saveArticle(article);
        return "redirect:/articles";
    }



    @GetMapping("/articles/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "edit_article";
    }

    @PostMapping("/articles/{id}")
    public String updateArticle(@PathVariable("id") int id,
                                @ModelAttribute Article article,
                                @RequestParam("imageFile") MultipartFile file) throws IOException {
        Article existing = articleService.getArticleById(id);
        existing.setTitle(article.getTitle());
        existing.setDescription(article.getDescription());

        if (!file.isEmpty()) {
            File folder = new File(uploadDir);
            if (!folder.exists()) folder.mkdirs();
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + "/" + fileName));
            existing.setImagePath(fileName);
        }
        articleService.saveArticle(existing);
        return "redirect:/articles";
    }

    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable("id") int id) {
        articleService.deleteArticleById(id);
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String viewArticle(@PathVariable int id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article_details";
    }

    @GetMapping("/learn")
    public String showPublicArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "article_list"; // This is the public-facing page
    }
    }
