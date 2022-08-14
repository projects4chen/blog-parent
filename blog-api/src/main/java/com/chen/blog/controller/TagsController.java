package com.chen.blog.controller;

import com.chen.blog.service.TagService;
import com.chen.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags")
public class TagsController {

    @Autowired
    private TagService tagService;

    // /tags/hot
    @GetMapping("hot")
    public Result hot(){
        int limit = 6;
        return tagService.hots(limit);
    }

    // /tags/hot
    @GetMapping()
    public Result findAll(){
        return tagService.findAll();
    }
}
