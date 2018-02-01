package com.lomoye.lion.web.filter;


import com.google.common.base.Strings;
import com.lomoye.lion.core.util.HttpCacheUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tommy on 2016/4/14.
 */
public class StaticCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (Strings.isNullOrEmpty(request.getParameter("v"))) {
            HttpCacheUtil.disableResponseCache(response);
        } else {
            HttpCacheUtil.enableResponseCache(response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
