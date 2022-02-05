package com.blog.service;

import com.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    User login(String userName,String password);


    User getUserById(int userId);

    User getUserByUserName(String userName);

    User getUserByNickName(String nickName);

    boolean addNewUser(String userName,String password,String nickName);

    boolean updatePwd(int userId,String newPassword);

    List<User> getUserList(int userId, String userName,String nickname);
    //封号和解封，status 为1代表解封,其它表示封号
    boolean updateUserStatus(int userId,int status);

    boolean deleteUser(int userId);
}
