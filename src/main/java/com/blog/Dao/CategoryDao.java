package com.blog.Dao;

import com.blog.entity.Category;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryDao {

    //返回分类list
    List<Category> getCategoryList(@Param("authorId") int authorId)throws Exception;

    int getCount(@Param("authorId") int authorId)throws Exception;

    Category getCategory(@Param("categoryId")int categoryId)throws Exception;

    List<Category> getAllCategory()throws Exception;

    Category getCategoryByName(@Param("categoryName")String categoryName)throws Exception;
    //模糊查询
    List<Category> getCategoryListByAll(@Param("categoryId")int category,@Param("categoryName")String categoryName)throws Exception;

    int addNewCategory(@Param("categoryName")String categoryName)throws Exception;

    int updateCategory(@Param("categoryId")int categoryId,@Param("categoryName")String categoryName)throws Exception;

    int deleteCategory(@Param("categoryId")int categoryId)throws Exception;
}
