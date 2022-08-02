package com.chen.blog.service;

import com.chen.blog.vo.Result;

public interface CommentsService {
    /**
     * 根据文章id查询所有的评论列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);
}
