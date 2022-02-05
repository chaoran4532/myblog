package com.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.blog.entity.*;
import com.blog.service.*;
import com.blog.util.Constants;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@SessionAttributes({Constants.ADMIN_SESSION,Constants.CODE})
public class AdminController {
    private AdminService adminService;
    private UserService userService;
    private CategoryService categoryService;
    private ArticleService articleService;
    private TagService tagService;
    @Autowired
    public AdminController(AdminService adminService, UserService userService,
                           CategoryService categoryService, ArticleService articleService, TagService tagService) {
        this.adminService = adminService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.tagService = tagService;
    }

    @RequestMapping("/login.jhtml")
    public String loginPage(){
        return "admin/login";
    }

    @RequestMapping("/")
    public String defaultPage(){
        return "redirect:admin.jhtml";
    }
    @RequestMapping("/admin.jhtml")
    public String adminPage(){
        return "admin/admin";
    }

    @RequestMapping("/login.action")
    public String login(String adminName, String password, String verCode, Model model, HttpServletResponse response){
        //验证 用户输入的验证码
        String SessionCode=(String)model.getAttribute(Constants.CODE);
        System.out.println(SessionCode);
        if(SessionCode==null){
            model.addAttribute(Constants.MESSAGE,"会话失效，请刷新页面");
            return "forward:login.jhtml";
        }
        //比较两组验证码，忽略大小写
        else if(!SessionCode.toLowerCase().equals(verCode.toLowerCase())){
            model.addAttribute(Constants.MESSAGE,"验证码错误");
            return "forward:login.jhtml";
        }

        Admin admin=adminService.login(adminName,password);
        if(admin==null){
            model.addAttribute(Constants.MESSAGE,"账号或密码错误");
            return "forward:login.jhtml";
        }
        else{
            Cookie cookie=new Cookie("adminName",adminName);
            response.addCookie(cookie);
            model.addAttribute(Constants.ADMIN_SESSION,admin);
            return "redirect:admin.jhtml";
        }
    }

    @RequestMapping("/logout.action")
    public String logout(SessionStatus status){
        status.setComplete();
        return "redirect:login.jhtml";
    }

    @RequestMapping("/userAdmin.jhtml")
    public String userAdminPage(String queryUserId,String queryUserName,String queryNickname,Model model){
        int userId=0;
        try{
            userId=Integer.parseInt(queryUserId);
        }catch (Exception e){
            userId=0;
        }
        List<User> userList=userService.getUserList(userId,queryUserName,queryNickname);
        model.addAttribute("userList",userList);
        model.addAttribute("queryUserId",queryUserId);
        model.addAttribute("queryUserName",queryUserName);
        model.addAttribute("queryNickname",queryNickname);
        return "admin/userAdmin";
    }
    @RequestMapping(path="/userDelete.action",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteUser(String userID){
        int userId=0;
        try{
            userId=Integer.parseInt(userID);
        }catch (Exception e){
            userId=0;
        }
        Map<String,String> resultMap=new HashMap<>();
        if(userId<=0){
            resultMap.put("delResult","notexist");
        }
        else {
            if(userService.deleteUser(userId))
                resultMap.put("delResult","true");
            else
                resultMap.put("delResult","false");
        }
        return JSONArray.toJSONString(resultMap);
    }
    @RequestMapping("/setUserStatus.action/{userID}/{status}")
    public String disableOrUnseal(@PathVariable("userID")String userID, @PathVariable("status") String status,Model model){
        int statusInt=0;
        int userId=0;
        try{
            statusInt=Integer.parseInt(status);
            userId=Integer.parseInt(userID);
        }
        catch (Exception e){
            return "404";
        }
        if(userService.updateUserStatus(userId,statusInt)){
            if(statusInt==1)
                model.addAttribute(Constants.MESSAGE,"解封成功");
            else
                model.addAttribute(Constants.MESSAGE,"封号成功");
        }
        else{
            model.addAttribute(Constants.MESSAGE,"封号失败");
        }
        return "forward:/admin/userAdmin.jhtml";
    }

    @RequestMapping("/articleAdmin.jhtml")
    public String articleAdminPage(String queryArticleName,String queryAuthorName,String queryCategory,String queryTag, Model model){
        List<Article> articleList=articleService.getArticle(queryAuthorName,queryArticleName,queryCategory,queryTag);
        List<Tag> tagList=tagService.getAllTag();
        List<Category> categoryList=categoryService.getAllCategory();

        model.addAttribute("articleList",articleList);
        //初始化类别，标签
        model.addAttribute("tagList",tagList);
        model.addAttribute("categoryList",categoryList);
        //将查询的文章名，类别id，标签id，重新返回前端
        model.addAttribute("queryAuthorName",queryAuthorName);
        model.addAttribute("queryArticleName",queryArticleName);
        model.addAttribute("queryCategory",queryCategory);
        model.addAttribute("queryTag",queryTag);
        return "admin/articleAdmin";
    }
    @RequestMapping(path = "/articleDelete.action",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String articleDelete(String articleID){
        int articleId=0;
        try{
            articleId=Integer.parseInt(articleID);
        }catch (Exception e){
            articleId=0;
        }
        Map<String,String> resultMap=new HashMap<>();
        if(articleId<=0){
            resultMap.put("delResult","notexist");
        }
        else {
            if(articleService.deleteArticle(articleId))
                resultMap.put("delResult","true");
            else
                resultMap.put("delResult","false");
        }
        String json=JSONArray.toJSONString(resultMap);
        return json;

    }

    @RequestMapping("/categoryAdmin.jhtml")
    public String categoryAdminPage(String queryCategoryId,String queryCategoryName,Model model){
        int categoryId=0;
        //如果id为空或不是数字，则设置id=0；
        try{
            categoryId=Integer.parseInt(queryCategoryId);
        }catch (Exception e){
            categoryId=0;
        }

        List<Category> categoryList=categoryService.getCategoryListByAll(categoryId,queryCategoryName);

       model.addAttribute("categoryList",categoryList);
       model.addAttribute("queryCategoryId",queryCategoryId);
       model.addAttribute("queryCategoryName",queryCategoryName);
       return "admin/categoryAdmin";
    }

    @RequestMapping("/categoryAdd.jhtml")
    public String categoryAddPage(){
        return "admin/categoryAdd";
    }
    @RequestMapping("/categoryAdd.action")
    public String categoryAdd(String newCategoryName,Model model){
        if(StringUtils.isNullOrEmpty(newCategoryName)){
            model.addAttribute(Constants.MESSAGE,"分类名不能为空");
        }
        else if(categoryService.addNewCategory(newCategoryName)){
            model.addAttribute(Constants.MESSAGE,"添加分类成功");
        }
        else {
            model.addAttribute(Constants.MESSAGE,"添加失败，已存在该分类");
        }
        return "forward:categoryAdd.jhtml";
    }

    @RequestMapping("/categoryUpdate.jhtml/{categoryId}")
    public String categoryUpdatePage(@PathVariable("categoryId")String categoryID,Model model){
        int categoryId=0;
        try{
            categoryId=Integer.parseInt(categoryID);
        }catch (Exception e){
            return "404";
        }
        Category category=categoryService.getCategory(categoryId);

        model.addAttribute("category",category);
        return "admin/categoryUpdate";
    }
    @RequestMapping("/categoryUpdate.action")
    public String categoryUpdate(String categoryID,String categoryName,Model model){
        int categoryId=0;
        try{
            categoryId=Integer.parseInt(categoryID);
        }catch (Exception e){
            categoryId=0;
        }
        if(StringUtils.isNullOrEmpty(categoryName)){
            model.addAttribute(Constants.MESSAGE,"分类名不能为空");
        }
        else if(categoryService.updateCategory(categoryId,categoryName)){
            model.addAttribute(Constants.MESSAGE,"修改分类成功");
        }
        else {
            model.addAttribute(Constants.MESSAGE,"修改失败，已存在该分类");
        }
        return "forward:categoryUpdate.jhtml/"+categoryID;
    }
    @RequestMapping(path="/categoryDelete.action",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteCategory(String categoryID){
        int categoryId=0;
        try{
            categoryId=Integer.parseInt(categoryID);
        }catch (Exception e){
            categoryId=0;
        }
        Map<String,String> resultMap=new HashMap<>();
        if(categoryId<=0){
            resultMap.put("delResult","notexist");
        }
        else {
            if(categoryService.deleteCategory(categoryId))
                resultMap.put("delResult","true");
            else
                resultMap.put("delResult","false");
        }
        return JSONArray.toJSONString(resultMap);
    }

    @RequestMapping("/tagAdmin.jhtml")
    public String tagAdminPage(String queryTagId, String queryTagName,Model model){
        int tagId=0;
        try{
            tagId=Integer.parseInt(queryTagId);
        }catch (Exception e){
            tagId=0;
        }

        List<Tag> tagList=tagService.getTagListByAll(tagId,queryTagName);
        model.addAttribute("tagList",tagList);
        model.addAttribute("queryTagId",queryTagId);
        model.addAttribute("queryTagName",queryTagName);
        return "admin/tagAdmin";
    }

    @RequestMapping("/tagAdd.jhtml")
    public String tagAddPage(){
        return "admin/tagAdd";
    }

    @RequestMapping("/tagAdd.action")
    public String tagAdd(String newTagName,Model model){
        if(StringUtils.isNullOrEmpty(newTagName)){
            model.addAttribute(Constants.MESSAGE,"标签名不能为空");
        }
        else if(tagService.addTag(newTagName)){
            model.addAttribute(Constants.MESSAGE,"添加成功");
        }
        else{
            model.addAttribute(Constants.MESSAGE,"添加失败，已存在该标签");
        }
        return "forward:/admin/tagAdd.jhtml";
    }

    @RequestMapping("/tagUpdate.jhtml/{tagId}")
    public String tagUpdatePage(@PathVariable("tagId")String tagID,Model model){
        int tagId=0;
        try{
            tagId=Integer.parseInt(tagID);
        }catch (Exception e){
            return "404";
        }
        Tag tag=tagService.getTag(tagId);
        model.addAttribute("tag",tag);
        return "/admin/tagUpdate";
    }
    @RequestMapping("/tagUpdate.action")
    public String tagUpdate(String tagID,String tagName,Model model){
        int tagId=0;
        try{
            tagId=Integer.parseInt(tagID);
        }catch (Exception e){
            tagId=0;
        }
        if(StringUtils.isNullOrEmpty(tagName)){
            model.addAttribute(Constants.MESSAGE,"标签名不能为空");
        }
        else if(tagService.updateTag(tagId,tagName)){
            model.addAttribute(Constants.MESSAGE,"修改成功");
        }
        else{
            model.addAttribute(Constants.MESSAGE,"修改失败，已存在该标签");
        }
        return "forward:tagUpdate.jhtml/"+tagID;
    }
    @RequestMapping(path="/tagDelete.action",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteTag(String tagID){
        int tagId=0;
        try{
            tagId=Integer.parseInt(tagID);
        }catch (Exception e){
            tagId=0;
        }
        Map<String,String> resultMap=new HashMap<>();
        if(tagId<=0){
            resultMap.put("delResult","notexist");
        }
        else {
            if(tagService.deletetag(tagId))
                resultMap.put("delResult","true");
            else
                resultMap.put("delResult","false");
        }
        return JSONArray.toJSONString(resultMap);
    }


}
