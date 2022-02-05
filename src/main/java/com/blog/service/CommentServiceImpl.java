package com.blog.service;

import com.blog.Dao.ArticleDao;
import com.blog.Dao.CommentDao;
import com.blog.entity.Comment;
import com.blog.util.DateUtils;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{
    private CommentDao commentDao;
    private ArticleDao articleDao;
    @Autowired
    public CommentServiceImpl(CommentDao commentDao,ArticleDao articleDao){
        this.commentDao=commentDao;
        this.articleDao=articleDao;
    }
    @Override
    public List<Comment> getCommentListByArticleId(int articleId) {
        List<Comment> commentList=null;
        try {
            commentList=commentDao.getCommentListByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentList;
    }

    @Override
    public boolean addNewComment(int articleId, String nickname, String content) {
        if(StringUtils.isNullOrEmpty(content))return false;
        boolean success=false;
        Comment comment=new Comment();
        comment.setArticle_id(articleId);
        comment.setNickname(nickname);
        comment.setContent(content);
        comment.setTime(DateUtils.getFormatDate(new Date()));
        try {
            if(commentDao.addNewComment(comment)>0)success=true;
            if(success)articleDao.updateCommentNum(1,articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deleteComment(int commentId) {
        boolean success=false;
        try {
            if(commentDao.deleteComment(commentId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Comment getComment(int commentId) {
        Comment comment=null;
        try {
            comment=commentDao.getComment(commentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public boolean starComment(int commentId) {
        boolean success=false;
        try {
            if(commentDao.updateStar(commentId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean dissComment(int commentId) {
        boolean success=false;
        try {
            if(commentDao.updateDiss(commentId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
