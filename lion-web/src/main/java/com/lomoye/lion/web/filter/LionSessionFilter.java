package com.lomoye.lion.web.filter;



import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by lomoye on 2017/7/1.
 * 检查用户是否登录
 */
public class LionSessionFilter implements Filter {
    private SessionManager sessionManager;

    @Override
    public void init(FilterConfig config) throws ServletException {
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        sessionManager = (SessionManager) springContext.getBean("sessionManagerImpl");
        if (sessionManager == null) {
            throw new RuntimeException("sessionManager should not be null");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getServletPath();
        if (path.contains("user/login") || path.contains("user/register")) {
            chain.doFilter(request, response);
            return;
        }

        //检查登录，以及更新登录的用户信息
        if (sessionManager.checkLogin(request)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setStatus(401);//401表示需要登录授权
    }

    @Override
    public void destroy() {

    }
}
