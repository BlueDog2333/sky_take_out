package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @AutoFill(OperationType.INSERT)
    @Insert("insert into category(`type`,`name`,sort,status,create_time,update_time,create_user,update_user)values(#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    public void createCategory(Category category);
    @Delete("delete from category where id=#{id}")
    void deleteCategory(Integer id);
    @Select("select * from category where type=#{type}")
    List<Category> listCategory(int type);
    @Select("select * from category where id=#{id}")
    Category getCategory(long id);
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
    @AutoFill(OperationType.UPDATE)
    void update(Category category);
}
