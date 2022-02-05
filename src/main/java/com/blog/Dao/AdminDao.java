package com.blog.Dao;


import com.blog.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    Admin getAdminByName(@Param("adminName") String adminName)throws Exception;
}
