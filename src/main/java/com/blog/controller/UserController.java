package com.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.entity.Tag;
import com.blog.entity.User;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.service.TagService;
import com.blog.service.UserService;
import com.blog.util.Constants;
import com.blog.util.DateUtils;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/user")
@SessionAttributes(Constants.USER_SESSION)
public class UserController {
    private UserService userService;
    private CategoryService categoryService;
    private ArticleService articleService;
    private TagService tagService;

    @Autowired
    public UserController(UserService userService, CategoryService categoryService, ArticleService articleService, TagService tagService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.tagService = tagService;
    }

    @RequestMapping("/addArticle.jhtml")
    public String addArticlePage(Model model) {

        List<Category> allCategory = categoryService.getAllCategory();
        List<Tag> allTag = tagService.getAllTag();

        model.addAttribute("allCategory", allCategory);
        model.addAttribute("allTag", allTag);
        return "user/add";
    }

    @PostMapping("addArticle.action")
    public String addArticle(String title, String introduction, String category, String tags, String content, Model model) {
        boolean isError = false;
        if (StringUtils.isNullOrEmpty(title) || StringUtils.isNullOrEmpty(category) || StringUtils.isNullOrEmpty(content)) {
            isError = true;
            model.addAttribute(Constants.MESSAGE, "内容和必填项不能为空");
        }
        if (!isError) {
            //将获取的数据进行处理封装
            Article article = new Article();
            article.setTitle(title);
            article.setCategory(category);
            article.setIntroduction(introduction);
            article.setContent(content);
            Date date = new Date();
            article.setTime(DateUtils.getFormatDate(date));
            List<String> tagNameList = Arrays.asList(tags.trim().split(" "));
            User blogger = (User) model.getAttribute(Constants.USER_SESSION);

            String msg = articleService.addArticle(blogger, article, tagNameList);
            if (!msg.equals("1")) {
                isError = true;
                model.addAttribute(Constants.MESSAGE, msg);
            }
        }
        if (isError) {
            //将用户已输入的内容重新返回去
            model.addAttribute("title", title);
            model.addAttribute("introduction", introduction);
            model.addAttribute("content", content);
            model.addAttribute("category", category);
            model.addAttribute("tags", tags);
            return "forward:/user/addArticle.jhtml";
        } else
            return "user/result";

    }
//    @RequestMapping(path = "/UploadPic",produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String uploadPic(){
//
//        // 返回的json
//        JSONObject jo = new JSONObject();
//
//        // 文件的保存目录
//        String savePath = this.getServletContext().getRealPath("/upload");
//        File saveFileDir = new File(savePath);
//        if (!saveFileDir.exists()) {
//            saveFileDir.mkdirs();
//        }
//        // 临时文件保存目录
//        String tmpPath = this.getServletContext().getRealPath("/upload/tem");
//        File tmpFile = new File(tmpPath);
//        if (!tmpFile.exists()) {
//            tmpFile.mkdirs();
//        }
//
//        String message = "";
//        try {
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            factory.setSizeThreshold(1024 * 10);
//            factory.setRepository(tmpFile);
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            upload.setFileSizeMax(1024 * 1024 * 3);
//
//            List items = upload.parseRequest(request);
//            if (items.size() != 0) {
//                FileItem item = (FileItem) items.get(0);
//                String fileName = item.getName();
//                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
//                if (item.getSize() > 1024 * 1024 * 3) {
//                    message = message + "文件：" + fileName + "，上传文件大小超过限制大小：" + upload.getFileSizeMax();
//                    jo.put("success", 0);
//                    jo.put("message", message);
//                } else {
//                    String saveFileName = makeFileName(fileName);
//                    InputStream is = item.getInputStream();
//                    FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);
//                    byte buffer[] = new byte[1024];
//                    int len = 0;
//                    while ((len = is.read(buffer)) > 0) {
//                        out.write(buffer, 0, len);
//                    }
//
//                    out.close();
//                    is.close();
//                    item.delete();
//                    message = message + "文件：" + fileName + "上传成功";
//
//                    String url = "/Blog/upload/" + saveFileName;
//                    jo.put("success", 1);
//                    jo.put("message", message);
//                    jo.put("url", url);
//                }
//
//            }
//        } catch (FileUploadBase.FileSizeLimitExceededException e) {
//            message = message + "上传文件大小超过限制";
//            jo.put("success", 0);
//            jo.put("message", message);
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        response.getWriter().print(jo);
//    }

    @RequestMapping("/personalCenter.jhtml")
    public String personalCenterPage() {
        return "user/left";
    }

    //兼顾页面显示和搜索
    @RequestMapping("/articleList.jhtml")
    public String articleListPage(String queryArticleName, String queryCategory, String queryTag, Model model) {
        User blogger = (User) model.getAttribute(Constants.USER_SESSION);
        List<Article> articleList = articleService.getArticle(blogger.getUserId(), queryArticleName, queryCategory, queryTag);
        List<Tag> tagList = tagService.getAllTag();
        List<Category> categoryList = categoryService.getAllCategory();

        model.addAttribute("articleList", articleList);
        //初始化类别，标签
        model.addAttribute("tagList", tagList);
        model.addAttribute("categoryList", categoryList);
        //将查询的文章名，类别id，标签id，重新返回前端
        model.addAttribute("queryArticleName", queryArticleName);
        model.addAttribute("queryCategory", queryCategory);
        model.addAttribute("queryTag", queryTag);
        return "user/articleList";
    }

    @RequestMapping("/pwdModify.jhtml")
    public String pwdModifyPage() {
        return "user/pwdmodify";
    }

    @RequestMapping("/pwdModify.action")
    public String pwdModify(String oldpassword, String newpassword, Model model, SessionStatus status) {
        if (StringUtils.isNullOrEmpty(oldpassword)) {
            oldpassword = "";
        }
        User user = (User) model.getAttribute(Constants.USER_SESSION);
        if (user != null && !StringUtils.isNullOrEmpty(newpassword)) {
            if (!oldpassword.equals(user.getUserPassword())) {
                model.addAttribute(Constants.MESSAGE, "旧密码输入错误");
            } else if (userService.updatePwd(user.getUserId(), newpassword)) {
                model.addAttribute(Constants.MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
                //清除session中的属性
                status.setComplete();
            } else {
                model.addAttribute(Constants.MESSAGE, "修改密码失败");
            }
        } else {
            model.addAttribute(Constants.MESSAGE, "修改密码失败");
        }
        return "forward:/user/pwdModify.jhtml";
    }

    @RequestMapping("/articleUpdate.jhtml/{articleId}")
    public String articleUpdatePage(@PathVariable("articleId") String articleID, Model model) {
        int articleId = 0;
        try {
            articleId = Integer.parseInt(articleID);
        } catch (Exception e) {
            return "404";
        }
        User blogger = (User) model.getAttribute(Constants.USER_SESSION);
        Article article = articleService.getArticlebyId(articleId);
        //如果文章作者和博主不是同一人，则不能修改
        assert blogger != null;
        if (article.getAuthorId() != blogger.getUserId()) {
            return "404";
        }
        List<Category> allCategory = categoryService.getAllCategory();
        List<Tag> allTag = tagService.getAllTag();

        model.addAttribute("allCategory", allCategory);
        model.addAttribute("allTag", allTag);
        model.addAttribute("articleId", articleId);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("introduction", article.getIntroduction());
        model.addAttribute("content", article.getContent());
        model.addAttribute("category", article.getCategory());
        List<Tag> tagList = tagService.getTagByArticleId(articleId);
        //将tagList转变为字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (Tag tag : tagList) {
            stringBuilder.append(tag.getTag()).append(" ");
        }
        String tags = stringBuilder.toString().trim();
        model.addAttribute("tags", tags);
        return "user/articleUpdate";
    }

    @RequestMapping("/articleUpdate.action")
    public String updateArticle(String articleId, String title, String introduction, String category, String tags, String content, Model model) {
        boolean isError = false;
        int aId = 0;
        try {
            aId = Integer.parseInt(articleId);
        } catch (Exception e) {
            return "404";
        }
        if (StringUtils.isNullOrEmpty(title) || StringUtils.isNullOrEmpty(category) || StringUtils.isNullOrEmpty(content)) {
            isError = true;
            model.addAttribute(Constants.MESSAGE, "内容和必填项不能为空");
        }
        if (!isError) {
            //将获取的数据进行处理封装
            List<String> tagNameList = Arrays.asList(tags.trim().split(" "));
            String msg = articleService.updateArticle(aId, title, introduction, category, content, tagNameList);
            if (!msg.equals("1")) {
                isError = true;
                model.addAttribute(Constants.MESSAGE, msg);
            }
        }
        if (isError) {
            //将用户已输入的内容重新返回去

            return "forward:/user/articleUpdate.jhtml/"+articleId;
        } else
            return "user/result";

    }

    @RequestMapping(path = "/articleDelete.action",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String articleDelete(String articleID,Model model){

        User user=(User)model.getAttribute(Constants.USER_SESSION);


        int articleId=0;
        try{
            articleId=Integer.parseInt(articleID);
        }catch (Exception e){
            articleId=0;
        }
        Map<String,String> resultMap=new HashMap<>();

        if(articleId<=0||user!=null&&user.getUserId()!=articleService.getArticlebyId(articleId).getAuthorId()){
            resultMap.put("delResult","notexist");
        }
        else {
            if(articleService.deleteArticle(articleId))
                resultMap.put("delResult","true");
            else
                resultMap.put("delResult","false");
        }
        String json= JSONArray.toJSONString(resultMap);
        return json;

    }
}
