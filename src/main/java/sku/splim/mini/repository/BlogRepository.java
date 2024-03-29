package sku.splim.mini.repository;
import org.hibernate.query.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sku.splim.mini.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface BlogRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Query("select "
            + "distinct q "
            + "from Article q "
            + "where "
            + "   q.title like %:kw% ")
    List<Article> findAll(@Param("kw") String sw);
}

