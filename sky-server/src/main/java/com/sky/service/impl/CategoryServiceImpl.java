package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
    {
        @Autowired
        CategoryMapper categoryMapper;
        @Override
        public void createCategory(CategoryDTO categoryDTO) {
            Category category =Category.builder()
                    .status(1).build();

            BeanUtils.copyProperties(categoryDTO, category);
            categoryMapper.createCategory(category);
        }

        @Override
        public void deleteCategory(Integer id) {
            categoryMapper.deleteCategory(id);
        }

        @Override
        public List<Category> listCategory(int type) {
            return categoryMapper.listCategory(type);
        }

        @Override
        public PageResult list(CategoryPageQueryDTO categoryPageQueryDTO) {
            PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
            Page<Category> categoryPage=categoryMapper.pageQuery(categoryPageQueryDTO);
            return new PageResult(categoryPage.getTotal(),categoryPage.getResult());

        }

        @Override
        public void stopOrStart(int status, long id) {
            Category category =categoryMapper.getCategory(id);
            category.setStatus(status);
            categoryMapper.update(category);

        }

        @Override
        public void put(CategoryDTO categoryDTO) {
            Category category = new Category();
            BeanUtils.copyProperties(categoryDTO, category);
            categoryMapper.update(category);
        }
    }
