package com.blog.Dao;

import com.blog.entity.Article;
import com.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagDao {
    //返回该用户所使用的标签
    List<Tag> getTagByUser(@Param("authorId")int authorId)throws Exception;

    int getCount(@Param("authorId")int authorId)throws Exception;
    //返回文章所使用的标签
    List<Tag> getTagByArticle(@Param("articleId")int articleId)throws Exception;

    Tag getTag(@Param("tagId")int tagId)throws Exception;

    List<Tag> getAllTag()throws Exception;
    //通过tag名字List返回这些tag
    List<Tag> getTagListByName(@Param("tagNameList") List<String> tagNameList)throws Exception;
    //向数据库中添加文章和标签的关系数据
    int addTagToArticle(@Param("tagList")List<Tag> tagList, @Param("articleId")int articleId)throws Exception;

    List<Tag> getTagByAll(@Param("tagId") int tagId,@Param("tagName")String tagName)throws Exception;

    int addNewTag(@Param("tagName")String tagName)throws Exception;

    int updateTag(@Param("tagId")int tagId,@Param("tagName")String tagName)throws Exception;

    int deleteTag(@Param("tagId")int tagId)throws Exception;

    int deleteTagToArticle(@Param("articleId")int articleId)throws Exception;
}
