package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SetmealMapper {
    @Select("select * from setmeal where id = #{id}")
    Setmeal findSetmealById(Long id);
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<Setmeal> page(SetmealPageQueryDTO setmealPageQueryDTO);

    void delete(List<Integer> ids);
    @Update("update setmeal set status = #{status} where id = #{id}")
    void setStatus(Integer status, Long id);
    @Insert("insert into setmeal_dish(setmeal_id,dish_id,`name`,price,copies) values(#{setmealId},#{dishId},#{name},#{price},#{copies})")
    void insertDish(SetmealDish setmealDish);
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteDish(Long setmealId);
}

