package com.macro.mall.security.component;

import org.springframework.security.core.userdetails.UserDetails;

public class UserHolder {
    private static final ThreadLocal<UserDetails> threadLocal = new ThreadLocal<>();

    public static UserDetails get() {
        return threadLocal.get();
    }
    public static void set(UserDetails user) {
        threadLocal.set(user);
    }
}
