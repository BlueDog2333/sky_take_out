package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    void createCategory(CategoryDTO categoryDTO);

    void deleteCategory(Integer id);

    List<Category> listCategory(int type);

    PageResult list(CategoryPageQueryDTO categoryPageQueryDTO);

    void stopOrStart(int status, long id);

    void put(CategoryDTO categoryDTO);
}
