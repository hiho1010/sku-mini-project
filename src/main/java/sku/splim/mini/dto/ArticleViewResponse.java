package sku.splim.mini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import me.shinsunyoung.springbootdeveloper.domain.Article;
=======
import sku.splim.mini.domain.Article;
>>>>>>> feature

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;

  public ArticleViewResponse(Article article) {
    this.id = article.getId();
    this.title = article.getTitle();
    this.content = article.getContent();
    this.createdAt = article.getCreatedAt();
  }
}
