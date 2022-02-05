package com.blog.controller;

import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.entity.Tag;
import com.blog.entity.User;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.service.TagService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {
    private UserService userService;
    private CategoryService categoryService;
    private ArticleService articleService;
    private TagService tagService;
    @Autowired
    public BlogController(UserService userService, CategoryService categoryService, ArticleService articleService, TagService tagService){
        this.userService=userService;
        this.categoryService=categoryService;
        this.articleService=articleService;
        this.tagService=tagService;
    }


    @RequestMapping("/homepage.jhtml/{bloggerID}")
    public String indexPage(@PathVariable String bloggerID,Model model){
        int bloggerId = 0;
        try{
            bloggerId=Integer.parseInt(bloggerID);
        }catch (Exception e){
            return "404";
        }
        //初始化分类栏
        List<Category> categoryList=categoryService.getCategoryList(bloggerId);
        List<Article>articleList=articleService.getArticle(bloggerId);
        List<Tag>tagList=tagService.getTagByUserid(bloggerId);
        User blogger=userService.getUserById(bloggerId);
        int articleCount=articleService.getCount(bloggerId);
        int categoryCount=categoryService.getCount(bloggerId);
        int tagCount=tagService.getCount(bloggerId);
        List<Article>articlesTopTen=articleService.getVisitedRank(bloggerId);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("articleList",articleList);
        //初始化标签
        model.addAttribute("tagList",tagList);
        //初始化博主信息
        model.addAttribute("blogger",blogger);
        //初始化日志、分类、标签的数量
        model.addAttribute("articleCount",articleCount);
        model.addAttribute("categoryCount",categoryCount);
        model.addAttribute("tagCount",tagCount);
        //初始化阅读排行
        model.addAttribute("articlesTopTen",articlesTopTen);
        return "homepage";
    }

    @RequestMapping("/category.jhtml/{bloggerID}")
    public String category(@PathVariable String bloggerID,Model model){
        int bloggerId=0;
        try{
            bloggerId=Integer.parseInt(bloggerID);
        }catch (Exception e){
            return "404";
        }
        User blogger=userService.getUserById(bloggerId);
        List<Category> categoryList=categoryService.getCategoryList(bloggerId);
        Map<String,List<Article>> articleListMap=new HashMap<>();
        //取得所有类别和文章列表的映射
        for(Category category:categoryList){
            articleListMap.put(category.getCategoryName(),articleService.getArticleByCategory(bloggerId,category.getId()));
        }
        model.addAttribute("blogger",blogger);
        model.addAttribute("articleListMap",articleListMap);
        return "categoryAll";
    }

    @RequestMapping("/category.jhtml/{bloggerID}/{categoryID}")
    public String categotyPage(@PathVariable String bloggerID,@PathVariable String categoryID,Model model){
        int bloggerId=0;
        int categoryId=0;
        try{
            bloggerId=Integer.parseInt(bloggerID);
            categoryId=Integer.parseInt(categoryID);
        }catch (Exception e){
            return "404";
        }
        User blogger=userService.getUserById(bloggerId);
        List<Article> articleList=articleService.getArticleByCategory(bloggerId,categoryId);
        Category category=categoryService.getCategory(categoryId);
        model.addAttribute("articleList",articleList);
        model.addAttribute("category",category);
        model.addAttribute("blogger",blogger);

        return "category";
    }

    @RequestMapping("tag.jhtml/{bloggerID}/{tagID}")
    public String TagPage(@PathVariable String bloggerID,@PathVariable String tagID,Model model){
        int bloggerId=0;
        int tagId=0;
        try{
            bloggerId=Integer.parseInt(bloggerID);
            tagId=Integer.parseInt(tagID);
        }catch (Exception e){
            return "404";
        }
        User blogger=userService.getUserById(bloggerId);
        List<Article> articleList=articleService.getArticleByTag(bloggerId,tagId);
        Tag tag= tagService.getTag(tagId);
        model.addAttribute("articleList",articleList);
        System.out.println(articleList);
        model.addAttribute("tag",tag);
        model.addAttribute("blogger",blogger);

        return "tags";
    }

    @RequestMapping("/about.jhtml")
    public String aboutPage(){
        return "about";
    }

    @RequestMapping("/")
    public String index(){
        return "redirect:homepage.jhtml/1";
    }


}
