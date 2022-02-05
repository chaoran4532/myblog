package com.blog.Interceptor;

import com.blog.util.Constants;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute(Constants.ADMIN_SESSION));
        if(session.getAttribute(Constants.ADMIN_SESSION)==null){
            response.sendRedirect(request.getContextPath()+"/admin/login.jhtml");
            return false;
        }
        return true;
    }
}
