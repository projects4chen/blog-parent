package com.chen.blog.controller;

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
    public Result listAritcle(@RequestBody PageParams pageParams){

        return articleService.listArticle(pageParams);
    }

    /**
     * 最热文章
     * @return
     */
    @PostMapping("hot")
    public Result hotAritcle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }
    /**
     * 最新文章
     */
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
