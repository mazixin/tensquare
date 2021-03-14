package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.search.controller
 * @author:MartinKing
 * @createTime:2021/3/11 15:44
 * @version:1.0
 */
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;
    @PostMapping()
    public Result save(@RequestBody Article article) {
        this.articleSearchService.save(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("/search/{key}/{page}/{size}")
    public Result searchByPage(@PathVariable String key,@PathVariable int page,@PathVariable int size) {
        Page<Article> articlePage = this.articleSearchService.findByTitleOrContentLike(key, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(articlePage.getTotalElements(), articlePage.getContent()));

    }


}
