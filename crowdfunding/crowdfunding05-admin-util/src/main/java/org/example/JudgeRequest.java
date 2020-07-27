package org.example;

import javax.servlet.http.HttpServletRequest;

public class JudgeRequest {
    /**
     *判断当前请求是否为Ajax请求
     * @param request 请求对象
     * @return
     *  true:当前请求为Ajax请求
     *  false：当前请求不是Ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request){
        String acceptHeader=request.getHeader("Accept");
        String xRequestHeader=request.getHeader("x-Requested-With");
        return (acceptHeader!=null&&acceptHeader.contains("application/json"))
                    ||
                (xRequestHeader!=null&&"XMLHttpRequest".equals(xRequestHeader));

    }
}
