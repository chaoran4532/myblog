package com.blog.service;

import com.blog.Dao.ArticleDao;
import com.blog.entity.Article;
import com.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ArticleService {

    List<Article> getArticle(int bloggerId);


    int getCount(int bloggerId);

    List<Article> getVisitedRank(int bloggerId);

    Article getArticlebyId(int articleId);

    Article getNextArticle(int authorId,String time);

    Article getPrevArticle(int authorId,String time);

    List<Article> getArticleByCategory(int bloggerId,int categoryId);

    List<Article> getArticleByTag(int bloggerId,int tagId);
    //如果添加成功返回1，否则返回错误信息。
    String  addArticle(User blogger, Article article,List<String> tagsName);

    String updateArticle(int articleId,String title,String introduction,String categoryName,String content,List<String> tagsName);

    List<Article> getArticle(int bloggerId,String queryArticleName,String queryCategoryID,String queryTagID);

    List<Article> getArticle(String queryAuthorName, String queryArticleName,String queryCategoryID,String queryTagID);

    boolean deleteArticle(int articleId);

    boolean starArticle(int articleId);

    boolean addVisit(int articleId);

}
