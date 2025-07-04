package com.snake.common.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    private RequestUtils() {
        // Utility class, prevent instantiation
    }

    public static HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) throw new IllegalStateException("No request context available");
        return attrs.getRequest();
    }

    public static String getMethod() {
        return getCurrentHttpRequest().getMethod();
    }

    public static String getRequestURI() {
        return getCurrentHttpRequest().getRequestURI();
    }

    public static String getPath() {
        return getCurrentHttpRequest().getServletPath();
    }

    public static Map<String, String> getHeaders() {
        HttpServletRequest req = getCurrentHttpRequest();
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, req.getHeader(name));
        }
        return headers;
    }

    public static Map<String, String[]> getParameters() {
        return getCurrentHttpRequest().getParameterMap();
    }

    public static String getRemoteAddr() {
        return getCurrentHttpRequest().getRemoteAddr();
    }

    public static String getQueryString() {
        return getCurrentHttpRequest().getQueryString();
    }

    public static Map<String, String> getCookies() {
        HttpServletRequest req = getCurrentHttpRequest();
        Map<String, String> cookiesMap = new HashMap<>();
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                cookiesMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookiesMap;
    }

    public static Map<String, Object> getSessionAttributes() {
        HttpServletRequest req = getCurrentHttpRequest();
        Map<String, Object> sessionAttrs = new HashMap<>();
        if (req.getSession(false) != null) {
            Enumeration<String> attrNames = req.getSession().getAttributeNames();
            while (attrNames.hasMoreElements()) {
                String name = attrNames.nextElement();
                sessionAttrs.put(name, req.getSession().getAttribute(name));
            }
        }
        return sessionAttrs;
    }
}
