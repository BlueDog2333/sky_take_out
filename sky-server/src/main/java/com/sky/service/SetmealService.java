package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface SetmealService {
    SetmealDTO findSetmealById(Long id);

    void setStatus(Integer status, Long id);

    void delete(List<Integer> ids);

    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void update(SetmealDTO setmealDTO);

    void insert(SetmealDTO setmealDTO);
}
