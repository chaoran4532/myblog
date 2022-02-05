package com.blog.Dao;

import com.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
        List<Comment> getCommentListByArticleId(@Param("articleId")int articleId)throws Exception;

        int addNewComment (Comment comment)throws Exception;

        int deleteComment(@Param("commentId")int commentId)throws Exception;

        Comment getComment(@Param("commentId")int commentId)throws Exception;

        int updateStar(@Param("commentId")int commentId)throws Exception;

        int updateDiss(@Param("commentId")int commentId)throws Exception;
}
