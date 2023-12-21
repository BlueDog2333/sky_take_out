package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    DishMapper dishMapper;

    @Override
    public void insert(DishDTO dishDTO) {
        Dish dish=new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.insert(dish);
        dishDTO.getFlavors().forEach(dishFlavor->{
            dishFlavor.setDishId(dish.getId());
            dishMapper.insertFlavor(dishFlavor);
        });




    }

    @Override
    public DishDTO findById(Integer id) {

        return dishMapper.findById(id);

    }

    @Override
    public List<DishDTO> list(int categoryId) {

        return dishMapper.list(categoryId);
    }

    @Override
    public PageResult getPage(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishDTO> dishDTOList=dishMapper.page(dishPageQueryDTO);
        return new PageResult(dishDTOList.getTotal(),dishDTOList.getResult());
    }

    @Override
    public void update(DishDTO dishDTO) {
        Dish dish=new Dish();
       BeanUtils.copyProperties(dishDTO,dish);
       dishMapper.update(dish);
    }

    @Override
    public void updateStatus(Integer status, int id) {
        dishMapper.updateStatus(status,id);

    }

    @Override
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            dishMapper.deleteFlavorByDishId(id);
        }
        dishMapper.delete(ids);
    }
}
