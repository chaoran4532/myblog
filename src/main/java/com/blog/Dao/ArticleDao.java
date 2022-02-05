package com.blog.Dao;

import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ArticleDao {
    //输入作者用户id，返回所有文章
    List<Article> getArticleList(@Param("authorId")int authorId)throws Exception;


    //返回作者用户id，返回文章数量
    int getCount(@Param("authorId") int authorId)throws Exception;
    //输入评论变化量-1或+1
    int updateCommentNum(@Param("commentChange")int commentChange,@Param("articleId")int articleId) throws Exception;
    //返回前阅读量前10的文章
    List<Article> getVisitedRank(@Param("authorId")int authorId) throws Exception;

    Article getArticleById(@Param("articleId")int articleId)throws Exception;

    Article getNextArticle(@Param("authorId")int authorId,@Param("time")Date time)throws Exception;

    Article getPrevArticle(@Param("authorId")int authorId,@Param("time")Date time)throws Exception;

    List<Article> getArticleByCategory(@Param("authorId")int authorId,@Param("categoryId")int categoryId) throws  Exception;

    List<Article> getArticleByTag(@Param("authorId")int authorId,@Param("tagId")int tagId)throws Exception;

    int addNewArticle(@Param("article")Article article , @Param("author")User author)throws Exception;

    int updateArticle(Article article)throws Exception;

    //为了获取新文章id，从数据库中读取新的文章数据
    Article getNewArticle(Article article);

    List<Article> getArticleListByAll(@Param("authorId") int authorId,@Param("articleName")String articleName,@Param("categoryId") int categoryId,@Param("tagId") int tagId)throws Exception;

    List<Article> getArticleListByAll2(@Param("authorName") String authorName,@Param("articleName")String articleName,@Param("categoryId") int categoryId,@Param("tagId") int tagId)throws Exception;

    int deleteArticle(@Param("articleId")int articleId)throws Exception;
    //点赞+1
    int updateStar(@Param("articleId")int articleId)throws Exception;
    //浏览量+1
    int updateVisit(@Param("articleId")int articleId)throws Exception;

}
