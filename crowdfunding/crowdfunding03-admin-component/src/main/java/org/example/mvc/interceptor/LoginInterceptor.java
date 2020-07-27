package org.example.mvc.interceptor;

import org.example.entity.Admin;
import org.example.mvc.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        Admin admin= (Admin) session.getAttribute("loginAdmin");
        if (admin==null){
            throw new AccessForbiddenException("请登录后进行访问");
        }
        return true;
    }
}
