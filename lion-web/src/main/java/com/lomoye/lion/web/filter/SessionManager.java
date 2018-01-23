package com.lomoye.lion.web.filter;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by lomoye on 2017/7/1.
 *
 */

public interface SessionManager {
    boolean checkLogin(HttpServletRequest request);
}
