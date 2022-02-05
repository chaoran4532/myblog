package com.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.blog.entity.Article;
import com.blog.entity.Comment;
import com.blog.entity.Tag;
import com.blog.entity.User;
import com.blog.service.ArticleService;
import com.blog.service.CommentService;
import com.blog.service.TagService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@SessionAttributes({"star", "visited","commentStarOrDiss"})
@Controller
public class ArticleController {
    private UserService userService;
    private ArticleService articleService;
    private TagService tagService;
    private CommentService commentService;

    @Autowired
    public ArticleController(UserService userService, ArticleService articleService, TagService tagService, CommentService commentService) {
        this.userService = userService;
        this.articleService = articleService;
        this.tagService = tagService;
        this.commentService = commentService;
    }

    @RequestMapping("/article.jhtml/{bloggerID}/{articleID}")
    public String articlePage(@PathVariable String bloggerID, @PathVariable String articleID, Model model) {
        int articleId = 0;
        int bloggerId = 0;
        try {
            bloggerId = Integer.parseInt(bloggerID);
            articleId = Integer.parseInt(articleID);
        } catch (Exception e) {
            return "404";
        }
        Set<Integer> isVisited = (Set<Integer>) model.getAttribute("visited");
        //判断在session周期内有没有阅读过该文章，若无，则文章浏览量+1
        if (isVisited == null) {
            Set<Integer> visitSet = new HashSet<>();
            visitSet.add(articleId);
            model.addAttribute("visited", visitSet);
            articleService.addVisit(articleId);
        } else if (!isVisited.contains(articleId)) {
            isVisited.add(articleId);
            articleService.addVisit(articleId);
        }
        User blogger = userService.getUserById(bloggerId);
        Article article = articleService.getArticlebyId(articleId);
        List<Tag> tagOfArticle = tagService.getTagByArticleId(articleId);
        Article nextArticle = articleService.getNextArticle(bloggerId, article.getTime());
        Article prevArticle = articleService.getPrevArticle(bloggerId, article.getTime());
        List<Comment> commentList = commentService.getCommentListByArticleId(articleId);

        model.addAttribute("blogger", blogger);
        //初始化文章信息
        model.addAttribute("article", article);
        //初始化文章标签
        model.addAttribute("tagOfArticle", tagOfArticle);
        //获取上一篇和下一篇文章
        model.addAttribute("nextArticle", nextArticle);
        model.addAttribute("prevArticle", prevArticle);
        //初始化评论
        model.addAttribute("commentList", commentList);
        return "article";
    }

    @RequestMapping("/addNewComment.action")
    public String addNewComment(String bloggerID, String articleID, String nickname, String content) {
        int articleId = 0;
        try {
            articleId = Integer.parseInt(articleID);
        } catch (Exception e) {
            return "404";
        }
        commentService.addNewComment(articleId, nickname, content);
        return "forward:article.jhtml/" + bloggerID + "/" + articleID;
    }

    @RequestMapping(path = "/articleStar.action", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String articleStar(String articleID, Model model) {
        int articleId = 0;
        try {
            articleId = Integer.parseInt(articleID);
        } catch (Exception e) {
            articleId = 0;
        }
        Map<String, String> resultMap = new HashMap<>();

        Set<Integer> isStar = (Set<Integer>) model.getAttribute("star");

        if (articleId <= 0) {
            resultMap.put("result", "error");
        }
        //判断该文章是否被点赞过
        else if (isStar != null && isStar.contains(articleId)) {
            resultMap.put("result", "false");
        } else {
            if (articleService.starArticle(articleId)) {
                Article article = articleService.getArticlebyId(articleId);
                resultMap.put("result", "true");
                resultMap.put("new_star", String.valueOf(article.getStar()));
                if (isStar == null) {
                    Set<Integer> starSet = new HashSet<>();
                    starSet.add(articleId);
                    model.addAttribute("star", starSet);
                } else {
                    isStar.add(articleId);
                }
            } else {
                resultMap.put("result", "false");
            }
        }
        return JSONArray.toJSONString(resultMap);
    }
    //评论的赞或者踩
    @RequestMapping(path="/commentStarOrDiss.action",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String starOrDissComment(String method,String commentID,Model model){
        int commentId=0;
        try{
            commentId=Integer.parseInt(commentID);
        }catch (Exception e){
            commentId=0;
        }
        Map<String,String>resultMap=new HashMap<>();
        //取出session中已经赞或评论过的Id集合
        Set<Integer> isStarOrDiss=(Set<Integer>)model.getAttribute("commentStarOrDiss");
        if (commentId <= 0) {
            resultMap.put("result", "error");
        }
        //判断该文章是否被点赞过
        else if (isStarOrDiss != null && isStarOrDiss.contains(commentId)) {
            resultMap.put("result", "false");
        } else {
            boolean success=false;
            Comment comment=commentService.getComment(commentId);
            if(method.equals("star")&&commentService.starComment(commentId)) {
                success = true;
                resultMap.put("new_star", String.valueOf(comment.getStar()+1));
            }
            else if(method.equals("diss")&&commentService.dissComment(commentId)) {
                success = true;
                resultMap.put("new_diss", String.valueOf(comment.getDiss()+1));
            }
            if (success) {
                resultMap.put("result", "true");
                if (isStarOrDiss == null) {
                    Set<Integer> starOrDissSet =  new HashSet<>();
                    starOrDissSet.add(commentId);
                    model.addAttribute("commentStarOrDiss", starOrDissSet);
                } else {
                    isStarOrDiss.add(commentId);
                }
            } else {
                resultMap.put("result", "false");
            }
        }
        return JSONArray.toJSONString(resultMap);
    }

    @RequestMapping(path = "/user/commentDelete.action", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteComment(String commentID) {
        int commentId = 0;
        try {
            commentId = Integer.parseInt(commentID);
        } catch (Exception e) {
            commentId = 0;
        }
        Map<String, String> resultMap = new HashMap<>();
        if (commentId <= 0) {
            resultMap.put("delResult", "notexist");
        } else {
            System.out.println(commentId);
            if (commentService.deleteComment(commentId))
                resultMap.put("delResult", "true");
            else
                resultMap.put("delResult", "false");
        }
        return JSONArray.toJSONString(resultMap);


    }
}
