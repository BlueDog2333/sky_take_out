package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


public interface DishService {


    void insert(DishDTO dishDTO);

    DishDTO findById(Integer id);

    List<DishDTO> list(int categoryId);

    PageResult getPage(DishPageQueryDTO dishPageQueryDTO);

    void update(DishDTO dishDTO);

    void updateStatus(Integer status, int id);

    void delete(List<Long> ids);
}
