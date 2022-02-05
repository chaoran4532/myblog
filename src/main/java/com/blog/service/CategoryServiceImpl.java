package com.blog.service;

import com.blog.Dao.CategoryDao;
import com.blog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryDao categoryDao;
    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao){
        this.categoryDao=categoryDao;
    }
    @Override
    public List<Category> getCategoryList(int bloggerId) {
        List<Category> categoryList=null;
        try {
            categoryList=categoryDao.getCategoryList(bloggerId);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public int getCount(int bloggerId) {
        int count=0;
        try {
            count=categoryDao.getCount(bloggerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Category getCategory(int categoryId) {
        Category category=null;
        try {
            category=categoryDao.getCategory(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;

    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> allCategory=null;
        try {
            allCategory=categoryDao.getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCategory;
    }

    @Override
    public List<Category> getCategoryListByAll(int categoryId,String categoryName) {
        List<Category> categoryList=null;
        try{
            categoryList=categoryDao.getCategoryListByAll(categoryId,categoryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public boolean addNewCategory(String categoryName) {
        boolean success=false;
        try {
            if(categoryDao.addNewCategory(categoryName)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean updateCategory(int categoryId, String categoryName) {
        boolean success=false;
        try {
            if(categoryDao.updateCategory(categoryId,categoryName)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        boolean success=false;
        try {
            if(categoryDao.deleteCategory(categoryId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


}
