package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.search
 * @author:MartinKing
 * @createTime:2021/3/11 15:42
 * @version:1.0
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
