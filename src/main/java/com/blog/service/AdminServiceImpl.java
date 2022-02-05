package com.blog.service;

import com.blog.Dao.AdminDao;
import com.blog.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    private AdminDao adminDao;
    @Autowired
    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin login(String adminName, String password) {
        Admin admin=null;
        try {
            admin=adminDao.getAdminByName(adminName);
            if(!admin.getPassword().equals(password))admin=null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}
