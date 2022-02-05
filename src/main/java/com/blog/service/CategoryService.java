package com.blog.service;

import com.blog.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    //返回分类列表,用户有该分类的文章才会显示
    List<Category> getCategoryList(int bloggerId);

    int getCount(int bloggerId);

    Category getCategory(int categoryId);

    List<Category>getAllCategory();

    List<Category> getCategoryListByAll(int categoryId,String categoryName);

    boolean addNewCategory(String categoryName);

    boolean updateCategory(int categoryId,String categoryName);

    boolean deleteCategory(int categoryId);
}
