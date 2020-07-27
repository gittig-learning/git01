package org.example.mvc.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.JudgeRequest;
import org.example.ResultEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView nullPointException(Exception ex
            ,HttpServletResponse response
            ,HttpServletRequest request) throws IOException {

        return repeatedCode("error",ex,request,response);

    }
    @ExceptionHandler(value = LoginErrorException.class)
    public ModelAndView loginErrorException(Exception ex
            ,HttpServletResponse response
            ,HttpServletRequest request) throws IOException {

        return repeatedCode("admin-login",ex,request,response);
    }

    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView accessForbiddenException(Exception ex
            ,HttpServletResponse response
            ,HttpServletRequest request) throws IOException {
        return repeatedCode("admin-login",ex,request,response);

    }
    @ExceptionHandler(value = RemoveLoginAdminException.class)
    public ModelAndView removeLoginAdminException(Exception ex
            ,HttpServletResponse response
            ,HttpServletRequest request) throws IOException {
        System.out.println("=====================removeLoginAdminException异常处理方法执行=====================");
        return repeatedCode("admin-page",ex,request,response);
    }
    @ExceptionHandler(value = AddAdminAcctException.class)
    public ModelAndView addAdminAcctException(Exception ex
            ,HttpServletResponse response
            ,HttpServletRequest request) throws IOException {
        return repeatedCode("admin-add",ex,request,response);
    }




    private ModelAndView repeatedCode(String viewUrl,Exception ex
            , HttpServletRequest request
            , HttpServletResponse response) throws IOException {
        boolean is= JudgeRequest.judgeRequestType(request);
        if(is==true){
            ResultEntity<Object> requestEntity=ResultEntity.failed(ex.getMessage());
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(requestEntity);
            response.setContentType("application/json;charset=UTF8");
            PrintWriter out=response.getWriter();
            out.println(json);
            return null;
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("exception",ex);
        modelAndView.setViewName(viewUrl);
        System.out.println("====================repeated方法执行，重定向======================");
        return modelAndView;
    }
}
