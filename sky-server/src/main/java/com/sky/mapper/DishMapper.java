package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {
    @AutoFill(OperationType.INSERT)
    public void insert(Dish dish);

    @Insert("insert into dish_flavor(dish_id,`name`,`value`) value(#{dishId},#{name},#{value})")
    public void insertFlavor(DishFlavor dishFlavor);
    @Select("select * from dish where id=#{id}")
    DishDTO findById(Integer id);
    @Select("select * from dish where category_id=#{categoryId}")
    List<DishDTO> list(int categoryId);

    Page<DishDTO> page(DishPageQueryDTO dishPageQueryDTO);
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);
    @AutoFill(OperationType.UPDATE)
    @Update("update dish set status=#{status} where id=#{id}")
    void updateStatus(Integer status, int id);
    @Delete("delete from dish_flavor where dish_id=#{id}")
    void deleteFlavorByDishId(Long id);

    void delete(List<Long> ids);
}
