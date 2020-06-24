package cn.javak.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: theTian
 * @date: 2020/3/25 22:30
 */
public class ThreadLocalUtils {
    private static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<HttpServletRequest> getInstance(){
        return requestThreadLocal;
    }
}
