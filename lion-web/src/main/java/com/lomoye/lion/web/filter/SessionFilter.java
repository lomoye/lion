package com.lomoye.lion.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lomoye on 2018/2/1.
 *
 */
public class SessionFilter implements Filter {
    private void configResponseHeader(HttpServletResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.addHeader("Cache-Control", "no-cache");// 浏览器和缓存服务器都不应该缓存页面信息
        response.addHeader("Cache-Control", "no-store");// 请求和响应的信息都不应该被存储在对方的磁盘系统中；
        response.addHeader("Cache-Control", "must-revalidate");// 于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        configResponseHeader(response);
        chain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
