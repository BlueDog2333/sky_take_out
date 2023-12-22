package com.sky.controller.admin;

import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.service.impl.SetmealServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
public class SetmealController {
    @Autowired
    SetmealService setmealService;
    @RequestMapping("/{id}")
    public Result<SetmealDTO> search(@PathVariable Long id){
        return Result.success(setmealService.findSetmealById(id));
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        setmealService.delete(ids);
        return Result.success();
    }
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult=setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }
    @PutMapping
    public Result update(@RequestBody SetmealDTO setmealDTO){
        setmealService.update(setmealDTO);
        return Result.success();
    }
    @PostMapping
    public Result insert(@RequestBody  SetmealDTO setmealDTO){
         setmealService.insert(setmealDTO);
         return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result setStatus(@PathVariable Integer status,Long id){
        setmealService.setStatus(status,id);
        return Result.success();}
    }



