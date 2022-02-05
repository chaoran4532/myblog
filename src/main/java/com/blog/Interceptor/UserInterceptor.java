package com.blog.Interceptor;

import com.blog.util.Constants;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute(Constants.USER_SESSION));
        if(session.getAttribute(Constants.USER_SESSION)==null){
            System.out.println(session.getAttribute(Constants.USER_SESSION));
            response.sendRedirect(request.getContextPath()+"/login.jhtml");
            return false;
        }
        return true;
    }
}
