package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.Constants;
import com.blog.util.VerificationCodeUtils;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@SessionAttributes({Constants.CODE,Constants.USER_SESSION})
public class AccountController {
    private UserService userService;
    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/verificationCode.gif")
    public void getVerificationCode(HttpServletResponse response, Model model) throws IOException {
        //设置验证码为不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //取得验证码和验证码图片，Object[0]为验证码字符串,Object[1]为验证码图片
        Object[] object = VerificationCodeUtils.getVerificationCode();

        String code=(String)object[0];
        BufferedImage image=(BufferedImage)object[1];

        OutputStream os = response.getOutputStream();

        model.addAttribute(Constants.CODE,code);

        ImageIO.write(image,"gif",os);

    }
    @RequestMapping("/login.jhtml")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("/login.action")
    public String login(String userName, String password, String verCode, Model model, HttpServletResponse response){
        //验证 用户输入的验证码
        String SessionCode=(String)model.getAttribute(Constants.CODE);
        if(SessionCode==null){
            model.addAttribute(Constants.MESSAGE,"会话失效，请刷新页面");
            return "forward:login.jhtml";
        }
        //比较两组验证码，忽略大小写
        else if(!SessionCode.toLowerCase().equals(verCode.toLowerCase())){
            model.addAttribute(Constants.MESSAGE,"验证码错误");
            return "forward:login.jhtml";
        }

        User user=userService.login(userName,password);
        if(user==null){
            model.addAttribute(Constants.MESSAGE,"账号或密码错误");
            return "forward:login.jhtml";
        }
        else{
            Cookie cookie=new Cookie("userName",userName);
            response.addCookie(cookie);
            model.addAttribute(Constants.USER_SESSION,user);
            return "redirect:/homepage.jhtml/"+user.getUserId();
        }
    }
    @RequestMapping("/logout.action")
    public String logout(SessionStatus status){
        status.setComplete();
        return "redirect:login.jhtml";
    }
    @RequestMapping("/register.jhtml")
    public String registerPage(){
        return "register";
    }
    @RequestMapping("/register.action")
    public String register(String userName,String nickname,String password,String repeatPassword,Model model){
        boolean success=true;
        //对用户的输入进行判断是否合规,若有一项不合规，其它规则就不进行比较了
        if(StringUtils.isNullOrEmpty(password) || !password.equals(repeatPassword)){
            model.addAttribute(Constants.MESSAGE,"两次密码不相同");
            success=false;
        }
        else if(StringUtils.isNullOrEmpty(userName) || userName.length()<6||userName.length()>20){
            model.addAttribute(Constants.MESSAGE,"用户名不能为空，且长度不能小于6,大于20");
            success=false;
        }
        else if(StringUtils.isNullOrEmpty(nickname)){
            model.addAttribute(Constants.MESSAGE,"昵称不能为空");
            success=false;
        }else if( password.length()<6||password.length()>20){
            model.addAttribute(Constants.MESSAGE,"密码长度不能小于6,大于20");
            success=false;
        }
        User user=userService.getUserByUserName(userName);
        //user如果不为空，说明数据库中已经有相同用户名的用户
        if(success&& user!=null){
            model.addAttribute(Constants.MESSAGE,"用户名已经占用");
            success=false;
        }
        user=userService.getUserByNickName(nickname);
        if(success&& user!=null){
            model.addAttribute(Constants.MESSAGE,"昵称已经占用");
            success=false;
        }
        //向数据库中添加新用户
        if(success) {
            if (!userService.addNewUser(userName, password, nickname)) {
                model.addAttribute(Constants.MESSAGE, "昵称已经占用");
                success = false;
            }
            else{
                model.addAttribute(Constants.MESSAGE,"注册成功");
            }
        }
        if(!success){
            //将用户已输入的数据返还
            model.addAttribute("userName",userName);
            model.addAttribute("nickname",nickname);
            model.addAttribute("password",password);
            model.addAttribute("repeatPassword",repeatPassword);
            return "forward:register.jhtml";
        }
        else{
            return "forward:login.jhtml";
        }
    }
}
