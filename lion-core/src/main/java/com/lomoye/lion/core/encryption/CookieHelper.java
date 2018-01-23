package com.lomoye.lion.core.encryption;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieHelper.class);

    private static final Gson gson = new Gson();


    public static void setCookie(HttpServletResponse response, String name, String path, Object value, int maxAge, String encryptKey) {
        String str = value == null ? "" : gson.toJson(value);
        if (!Strings.isNullOrEmpty(encryptKey)) {
            str = new AesEncryption(encryptKey).serialize(str);
        }
        setCookie(response, name, path, str, maxAge);
    }

    public static void setCookie(HttpServletResponse response, String name, String path, String value, int maxAge) {
        if (Strings.isNullOrEmpty(value) || Strings.isNullOrEmpty(name)) {
            return;
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static <T> T getCookie(HttpServletRequest request, String name, Class<T> clazz, String encryptKey) {
        String cookieValue = getCookie(request, name);

        if (cookieValue == null) {
            return null;
        }

        if (!Strings.isNullOrEmpty(encryptKey)) {
            cookieValue = new AesEncryption(encryptKey).deserialize(cookieValue);
        }
        return gson.fromJson(cookieValue, clazz);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        String cookieValue = null;
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                cookieValue = cookies[i].getValue();
                break;
            }
        }
        return cookieValue;
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String path, String name) {
        Cookie cookie = null;
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                cookie = cookies[i];
                break;
            }
        }

        if (cookie != null) {
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }
}
