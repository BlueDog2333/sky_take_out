package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public Result createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return Result.success();
    }
    @DeleteMapping
    public Result deleteCategory(@RequestParam Integer id){
        categoryService.deleteCategory(id);
        return Result.success();
    }
    @GetMapping("/list")
    public Result<List<Category>> listCategory(int type){
        List<Category> categories=categoryService.listCategory(type);
        return Result.success(categories);
    }
    @GetMapping("/page")
    public Result<PageResult> list(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult=categoryService.list(categoryPageQueryDTO);
        return Result.success(pageResult);
    }
    @PostMapping("/status/{status}")
    public Result stopOrStart(@PathVariable int status,long id){
        categoryService.stopOrStart(status,id);
        return Result.success();

    }
    @PutMapping
    public Result put(@RequestBody CategoryDTO categoryDTO){
        categoryService.put(categoryDTO);
        return Result.success();
    }

}
