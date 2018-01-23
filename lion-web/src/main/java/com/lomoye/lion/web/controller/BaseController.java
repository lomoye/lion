package com.lomoye.lion.web.controller;

import com.lomoye.lion.core.constant.SessionConstant;
import com.lomoye.lion.core.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lomoye on 2017/7/1.
 *
 */
public class BaseController {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    protected User getSessionUser(HttpServletRequest request) {
        return (User)request.getSession().getAttribute(SessionConstant.USER);
    }


}
