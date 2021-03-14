package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.search.service
 * @author:MartinKing
 * @createTime:2021/3/11 15:43
 * @version:1.0
 */
@Service
@Transactional
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;

    public void save(Article article) {
        this.articleSearchDao.save(article);
    }

    public Page<Article> findByTitleOrContentLike(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return this.articleSearchDao.findByTitleOrContentLike(key, key, pageable);
    }
}
