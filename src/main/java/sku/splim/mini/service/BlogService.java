package sku.splim.mini.service;

import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Article> searchArticles(String keyword) {
        return blogRepository.findAll(keyword);
    }

    private Specification<Article> search(String kw){
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (kw == null || kw.isEmpty()) {
                    return null; // No filtering if the keyword is empty
                }
                query.distinct(true);
                String pattern = "%" + kw.toLowerCase() + "%"; // Case-insensitive search
                return cb.like(root.get("title"), pattern);
                //return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), pattern);
            }
        };
    }
}
