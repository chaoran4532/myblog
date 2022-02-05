package com.blog.service;

import com.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentListByArticleId(int articleId);

    boolean addNewComment(int articleId,String nickname,String content);

    boolean deleteComment(int commentId);

    Comment getComment(int commentId);

    boolean starComment(int commentId);

    boolean dissComment(int commentId);
}
