package com.chen.blog.service;

import com.chen.blog.vo.Result;
import com.chen.blog.vo.TagVo;

import java.util.List;

public interface TagService {

    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);
}
