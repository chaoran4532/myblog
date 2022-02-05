package com.blog.service;

import com.blog.Dao.UserDao;
import com.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public User login(String userName, String password) {
        User user=null;
        try {
            user=userDao.getUserByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user!=null &&user.getUserPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public User getUserById(int userId) {
        User user=null;
        try{
            user=userDao.getUserById(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user=null;
        try{
            user=userDao.getUserByName(userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByNickName(String nickName) {
        User user=null;
        try{
            user=userDao.getUserByNickname(nickName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addNewUser(String userName, String password, String nickName) {
        User user=new User();
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setNickName(nickName);
        boolean success=false;
        try{
            if(userDao.addNewUser(user)>0)success=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean updatePwd(int userId, String newPassword) {
        boolean success=false;
        try {
            if(userDao.updatePwd(userId,newPassword)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<User> getUserList(int userId, String userName, String nickname) {
        List<User> userList=null;
        try {
            userList=userDao.getUserList(userId,userName,nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean updateUserStatus(int userId,int status) {
        boolean success=false;
        if(status!=0&&status!=1)return false;
        try {
            if(userDao.updateUserStatus(userId,status)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deleteUser(int userId) {
        boolean success=false;
        try{
            if(userDao.deleteUser(userId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

}
