package com.lomoye.lion.web.common;

import com.lomoye.common.exception.BusinessException;
import com.lomoye.common.util.NetUtils;
import com.lomoye.lion.core.constant.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lomoye on 2017/7/5.
 * 错误异常处理器
 */
@Component
public class ExceptionHandler extends SimpleMappingExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) {
        String viewName = determineViewName(ex, request);
        if (viewName != null) {// JSP格式返回
            LOGGER.warn("resolver doResolveException with viewName|" + viewName, ex);
            return super.doResolveException(request, response, arg2, ex);
        } else {// JSON格式返回
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            ModelAndView mav = new ModelAndView();
            if (ex instanceof BusinessException) {
                BusinessException bex = (BusinessException) ex;
                LOGGER.warn("Encountered business error|" + bex.getErrorCode() + "|" + bex.getErrorMessage());
                String code = bex.getErrorCode();
                if (ErrorCode.PARAMETER_IS_ILLEGAL.equals(code)) {
                    String message = bex.getErrorMessage() == null ? "请重新设置后提交,您传入的参数非法." : bex.getErrorMessage();
                    Map<String, String> models = new HashMap<>();
                    models.put("resultMessage", message);
                    models.put("resultCode", code);
                    mav.setView(view);
                    mav.addAllObjects(models);
                    return mav;
                } else {
                    Map<String, String> models = new HashMap<>();
                    models.put("resultMessage", bex.getErrorMessage());
                    models.put("resultCode", code);
                    mav.setView(view);
                    mav.addAllObjects(models);
                    return mav;
                }
            } else {
                if (ex.getClass().getSimpleName().equals("ClientAbortException")) {
                    LOGGER.warn(NetUtils.getRequestRealIp(request) + " |Tomcat broken pipe exception...|" + request.getRequestURL() + "|" + request.getQueryString());
                    //不显示exception日志
                } else {
                    LOGGER.warn(NetUtils.getRequestRealIp(request) + "|resolver doResolveException ...|" + request.getRequestURL() + "|" + request.getQueryString(), ex);
                }
            }
            return super.doResolveException(request, response, arg2, ex);
        }
    }
}
