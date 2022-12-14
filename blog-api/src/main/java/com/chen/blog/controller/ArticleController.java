package com.chen.blog.controller;

import com.chen.blog.common.aop.LogAnnotation;
import com.chen.blog.common.cache.Cache;
import com.chen.blog.service.ArticleService;
import com.chen.blog.vo.ArticleVo;
import com.chen.blog.vo.Result;
import com.chen.blog.vo.params.ArticleParam;
import com.chen.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// json数据进行交互
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    /**
     * 首页 文章列表
     * @param pageParams
     * @return
     */
    @PostMapping
    // 加上此注解，代表要对此接口记录日志
    @LogAnnotation(module="文章", operator="获取文章列表")
    @Cache(expire = 5 * 60 * 1000, name = "list_article")
    public Result listAritcle(@RequestBody PageParams pageParams){
        return articleService.listArticle(pageParams);
    }

    /**
     * 最热文章
     * @return
     */
    @PostMapping("hot")
    @Cache(expire = 5 * 6 * 1000, name="hot_article")
    public Result hotAritcle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }
    /**
     * 最新文章
     */
    @Cache(expire = 5 * 60 * 1000, name = "news_article")
    @PostMapping("new")
    public Result newAritcles(){
        int limit = 5;
        return articleService.newArticles(limit);
    }
    /**
     * 文章归档
     */
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId) {
        return articleService.findArticleById(articleId);
    }

    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }
}
