package com.blog.service;

import com.blog.Dao.ArticleDao;
import com.blog.Dao.CategoryDao;
import com.blog.Dao.TagDao;
import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.entity.Tag;
import com.blog.entity.User;
import com.blog.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService{
    private ArticleDao articleDao;
    private CategoryDao categoryDao;
    private TagDao tagDao;
    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, CategoryDao categoryDao, TagDao tagDao) {
        this.articleDao = articleDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
    }


    @Override
    public List<Article> getArticle(int bloggerId) {
        List<Article> articleList=null;
        try{
            articleList=articleDao.getArticleList(bloggerId);
        }catch (Exception e){
            e.printStackTrace();;
        }
        return articleList;
    }

    @Override
    public int getCount(int bloggerId) {
        int count=0;
        try {
            count=articleDao.getCount(bloggerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Article> getVisitedRank(int bloggerId) {
        List<Article> articlesTopTen=null;
        try {
            articlesTopTen=articleDao.getVisitedRank(bloggerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articlesTopTen;
    }

    @Override
    public Article getArticlebyId(int articleId) {
        Article article=null;
        try{
            article=articleDao.getArticleById(articleId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article getNextArticle(int authorId,String time) {
        Article nextArticle=null;

        try{
            nextArticle=articleDao.getNextArticle(authorId, DateUtils.getDate(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextArticle;
    }

    @Override
    public Article getPrevArticle(int authorId, String time) {
        Article prevArticle=null;
        try{
            prevArticle=articleDao.getPrevArticle(authorId,DateUtils.getDate(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prevArticle;
    }

    @Override
    public List<Article> getArticleByCategory(int bloggerId, int categoryId) {
        List<Article> articleList=null;
        try {
            articleList=articleDao.getArticleByCategory(bloggerId,categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public List<Article> getArticleByTag(int bloggerId, int tagId) {
        List<Article> articleList=null;
        try {
            articleList=articleDao.getArticleByTag(bloggerId,tagId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public String addArticle(User blogger, Article article,List<String> tagsName) {
        Category category=null;
        List<Tag> tags=null;
        //?????????????????????????????????id???
        try {
            category=categoryDao.getCategoryByName(article.getCategory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //??????????????????????????????????????????????????????????????????????????????
        if(category==null)return "???????????????";

        //?????????????????????????????????id???
        try{
            tags=tagDao.getTagListByName(tagsName);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(tags==null||tagsName.size()!=tags.size()){
            return "?????????????????????";
        }
        boolean success=true;
        try{
            article.setCategoryId(category.getId());
            if(articleDao.addNewArticle(article,blogger)<1)success=false;
            System.out.println(success);
            article=articleDao.getNewArticle(article);
            System.out.println(success);
            if(tagDao.addTagToArticle(tags,article.getId())<1)success=false;
            System.out.println(success);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(success)
            return "1";
        else
            return "??????????????????";
    }

    @Override
    public String updateArticle(int articleId,String title, String introduction, String categoryName, String content, List<String> tagsName) {
        Category category=null;
        List<Tag> tags=null;
        //?????????????????????????????????id???
        try {
            category=categoryDao.getCategoryByName(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //??????????????????????????????????????????????????????????????????????????????
        if(category==null)return "???????????????";

        //?????????????????????????????????id???
        try{
            tags=tagDao.getTagListByName(tagsName);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(tags==null||tagsName.size()!=tags.size()){
            return "?????????????????????";
        }
        boolean success=true;
        Article article=new Article();
            article.setId(articleId);
            article.setTitle(title);
            article.setIntroduction(introduction);
            article.setContent(content);
            article.setCategoryId(category.getId());
        try{
            if(articleDao.updateArticle(article)<1)success=false;
            if(tagDao.deleteTagToArticle(article.getId())<1)success=false;
            if(tagDao.addTagToArticle(tags,article.getId())<1)success=false;
        }catch (Exception e){
            e.printStackTrace();
        }
        if(success)
            return "1";
        else
            return "??????????????????";
    }

    @Override
    public List<Article> getArticle(int bloggerId, String queryArticleName, String queryCategoryID, String queryTagID) {
        int queryCategoryId=0;
        int queryTagId=0;
        try {
            queryCategoryId=Integer.parseInt(queryCategoryID);
            queryTagId=Integer.parseInt(queryTagID);
        }catch (Exception e){
            System.out.println("?????????Id???????????????");
        }
        List<Article>articleList=null;
        try {
            articleList=articleDao.getArticleListByAll(bloggerId,queryArticleName,queryCategoryId,queryTagId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(articleList);
        return articleList;
    }

    @Override
    public List<Article> getArticle(String queryAuthorName, String queryArticleName, String queryCategoryID, String queryTagID) {
        int queryCategoryId=0;
        int queryTagId=0;
        //???String?????????Id??????int
        try {
            queryCategoryId=Integer.parseInt(queryCategoryID);
            queryTagId=Integer.parseInt(queryTagID);
        }catch (Exception e){
            System.out.println("?????????Id???????????????");
        }

        List<Article>articleList=null;
        try {
            articleList=articleDao.getArticleListByAll2(queryAuthorName,queryArticleName,queryCategoryId,queryTagId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public boolean deleteArticle(int articleId) {
        boolean success=false;
        try {
            if(articleDao.deleteArticle(articleId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean starArticle(int articleId) {
        boolean success=false;
        try{
            if(articleDao.updateStar(articleId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean addVisit(int articleId) {
        boolean success=false;
        try{
            if(articleDao.updateVisit(articleId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


}
