package sku.splim.mini.dto;

import lombok.Getter;
import sku.splim.mini.domain.Article;

@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final int viewCount;
    private final int likeCount;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.viewCount = article.getViewCount();
        this.likeCount = article.getLikeCount();
    }
}
