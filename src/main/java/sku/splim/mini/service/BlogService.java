package sku.splim.mini.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import sku.splim.mini.domain.Article;
import sku.splim.mini.dto.AddArticleRequest;
import sku.splim.mini.dto.UpdateArticleRequest;
import sku.splim.mini.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }

    @Transactional
    public Article getViewCount(long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        // 조회수 증가
        article.increaseViewCount();
        return article;
    }

    @Transactional
    public Article getLikeCount(long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.increaseLikeCount();
        return article;
    }

    public List<Article> searchArticles(String keyword) {
        return blogRepository.findAll(keyword);
    }

}
