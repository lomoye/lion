package com.lomoye.lion.web.config.shiro;


import com.lomoye.common.dto.ResultData;
import com.lomoye.common.util.SerializationUtil;
import com.lomoye.lion.core.constant.ErrorCode;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author: hxy
 * @description: 对没有登录的请求进行拦截, 全部返回json信息. 覆盖掉shiro原本的跳转login.jsp的拦截方式
 * @date: 2017/10/24 10:11
 */
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            ResultData<Boolean> resultData = new ResultData<>();
            resultData.setResultCode(ErrorCode.LOGIN_EXPIRED);
            resultData.setResultMessage("登陆已过期,请重新登陆");
            resultData.setData(false);

            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(SerializationUtil.gson2String(resultData));
        } catch (Exception e) {
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(AjaxPermissionsAuthorizationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
