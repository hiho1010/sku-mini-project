package sku.splim.mini.controller;

import lombok.RequiredArgsConstructor;
import sku.splim.mini.domain.Article;
import sku.splim.mini.dto.ArticleListViewResponse;
import sku.splim.mini.dto.ArticleViewResponse;
import sku.splim.mini.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model, @RequestParam(value="kw", defaultValue = "") String kw) {
        List<ArticleListViewResponse> articles;
        if (!kw.isEmpty()) {
            articles = blogService.searchArticles(kw)
                    .stream()
                    .map(ArticleListViewResponse::new)
                    .toList();
        } else {
            articles = blogService.findAll()
                    .stream()
                    .map(ArticleListViewResponse::new)
                    .toList();
        }
        model.addAttribute("articles", articles);
        model.addAttribute("kw", kw);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.getViewCount(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }

    @GetMapping("/articles/like/{id}")
    public String like(@PathVariable Long id, Model model) {
        Article article = blogService.getLikeCount(id);
        article.increaseLikeCount();
        model.addAttribute("article", new ArticleViewResponse(article));
        return "redirect:/articles";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
