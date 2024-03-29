package sku.splim.mini.dto;

import lombok.Getter;
<<<<<<< HEAD
import me.shinsunyoung.springbootdeveloper.domain.Article;
=======
import sku.splim.mini.domain.Article;
>>>>>>> feature

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
