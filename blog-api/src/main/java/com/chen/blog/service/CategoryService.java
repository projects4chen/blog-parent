package com.chen.blog.service;

import com.chen.blog.vo.CategoryVo;

public interface CategoryService {

    CategoryVo findCategoryById(Long categoryId);
}