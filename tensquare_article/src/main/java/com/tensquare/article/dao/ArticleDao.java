package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    @Modifying //增删改需要加这个注解
    @Query(value = "UPDATE tb_article SET state=1 WHERE id=?1",nativeQuery = true)
    public void updateState(String articleId);

    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup = IFNULL(thumbup,0)+1 WHERE id=?",nativeQuery = true)
    public void addThumbup(String articleId);
}
