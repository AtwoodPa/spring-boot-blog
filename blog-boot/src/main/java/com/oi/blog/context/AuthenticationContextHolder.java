package com.oi.blog.context;

import org.springframework.security.core.Authentication;

/**
 * 身份验证信息上下文
 *
 * @author supanpan
 * @date 2024/07/16
 */
public class AuthenticationContextHolder {
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext() {
        return contextHolder.get();
    }

    public static void setContext(Authentication context) {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}
