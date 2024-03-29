package sku.splim.mini.dto;

import lombok.Getter;
<<<<<<< HEAD
import me.shinsunyoung.springbootdeveloper.domain.Article;
=======
import sku.splim.mini.domain.Article;
>>>>>>> feature

@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
