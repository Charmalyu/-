package com.imooc.sm.global;

import javax.servlet.*;
import java.io.IOException;

//编码过滤器
public class EncodingFilter implements Filter {

    private String encoding ="UTF-8";

    //初始化方法
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterConfig.getInitParameter("ENCODING") != null)
        encoding = filterConfig.getInitParameter("ENCODING");//获取初始化参数并赋值给encoding
    }

    //过滤方法
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);//请求
        servletResponse.setCharacterEncoding(encoding);//响应
        filterChain.doFilter(servletRequest,servletResponse);
    }

    //销毁方法
    public void destroy() {
        encoding=null;
    }
}
