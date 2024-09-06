package com.example.demo.service;

import org.springframework.security.core.context.SecurityContextHolder;

public class Sample {
    public String getLoggedInUser() {
        // これだけでユーザ名を取得可能！
        final String name = SecurityContextHolder.getContext().getAuthentication().getName();

        // DB等からユーザ情報を取得する処理
        System.out.println(name);
        return name;
    }
}


