package sku.splim.mini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sku.splim.mini.domain.Article;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;
  private int viewCount;
  private int likeCount;

  public ArticleViewResponse(Article article) {
    this.id = article.getId();
    this.title = article.getTitle();
    this.content = article.getContent();
    this.createdAt = article.getCreatedAt();
    this.viewCount = article.getViewCount();
    this.likeCount = article.getLikeCount();
  }
}
