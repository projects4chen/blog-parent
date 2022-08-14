package com.chen.blog.service;

import com.chen.blog.vo.CategoryVo;
import com.chen.blog.vo.Result;

public interface CategoryService {

    CategoryVo findCategoryById(Long categoryId);

    Result findAll();

    Result findAllDetail();

    Result categoryDetailById(Long id);
}