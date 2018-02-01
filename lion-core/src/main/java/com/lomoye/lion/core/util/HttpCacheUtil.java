package com.lomoye.lion.core.util;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class HttpCacheUtil {

    public static void enableResponseCache(HttpServletResponse response) {
        Date date = new Date();
        response.setDateHeader("Last-Modified", date.getTime()); //Last-Modified:页面的最后生成时间
        response.setDateHeader("Expires", date.getTime() + 12 * 60 * 60 * 1000); //Expires:过时期限值
        response.setHeader("Cache-Control", "public"); //Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
        response.setHeader("Pragma", "Pragma"); //Pragma:设置页面是否缓存，为Pragma则缓存，no-cache则不缓存
    }

    public static void disableResponseCache(HttpServletResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.addHeader("Cache-Control", "no-cache");// 浏览器和缓存服务器都不应该缓存页面信息
        response.addHeader("Cache-Control", "no-store");// 请求和响应的信息都不应该被存储在对方的磁盘系统中；
        response.addHeader("Cache-Control", "must-revalidate");// 于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
    }
}