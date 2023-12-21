package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

@RestController
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    DishService dishService;
    @PostMapping
    public Result insert(@RequestBody DishDTO dishDTO){
        dishService.insert(dishDTO);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result<DishDTO> findById(@PathVariable Integer id){
        DishDTO dishDTO=dishService.findById(id);
        return Result.success(dishDTO);
    }
    @GetMapping("/list")
    public Result<List<DishDTO>> list(int categoryId){
        List<DishDTO> list=dishService.list(categoryId);
        return Result.success(list);
    }
    @GetMapping("/page")
    public Result<PageResult> getPage(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult=dishService.getPage(dishPageQueryDTO);
        return Result.success(pageResult);
    }
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){
        dishService.update(dishDTO);
        return Result.success();
    }
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status,int id){
        dishService.updateStatus(status,id);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        dishService.delete(ids);
        return Result.success();
    }

}
