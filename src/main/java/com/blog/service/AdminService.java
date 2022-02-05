package com.blog.service;

import com.blog.entity.Admin;

public interface AdminService {
    Admin login(String adminName,String password);
}
