package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealMapper setmealMapper;

    public SetmealDTO findSetmealById(Long id) {
        SetmealDTO setmealDTO=new SetmealDTO();
        Setmeal setmeal=setmealMapper.findSetmealById(id);
        BeanUtils.copyProperties(setmeal,setmealDTO);
        return setmealDTO;
    }

    @Override
    public void setStatus(Integer status, Long id) {
        setmealMapper.setStatus(status,id);
    }

    @Override
    public void delete(List<Integer> ids) {
        setmealMapper.delete(ids);
    }

    @Override
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<Setmeal> pages=setmealMapper.page(setmealPageQueryDTO);

        return new PageResult(pages.getTotal(),pages.getResult());
    }

    @Override
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal=new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.update(setmeal);
        setmealMapper.deleteDish(setmeal.getId());
        for (SetmealDish setmealDish : setmealDTO.getSetmealDishes()) {
            setmealMapper.insertDish(setmealDish);
        }
    }

    @Override
    public void insert(SetmealDTO setmealDTO) {
        Setmeal setmeal=new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.insert(setmeal);
        for (SetmealDish setmealDish : setmealDTO.getSetmealDishes()) {
            setmealMapper.insertDish(setmealDish);
        }

    }



}
