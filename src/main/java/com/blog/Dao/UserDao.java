package com.blog.Dao;

import com.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

     User getUserByName(@Param("userName") String userName)throws Exception;

    User getUserById(@Param("userId")int userId)throws Exception;

    User getUserByNickname(@Param("nickname")String nickname)throws Exception;

    int addNewUser(User user)throws Exception;

    int updatePwd(@Param("userId") int userId,@Param("password") String password)throws Exception;

    List<User> getUserList(@Param("userId")int userId,@Param("userName")String userName,@Param("nickname")String nickname)throws Exception;

    int updateUserStatus(@Param("userId")int userId,@Param("status")int status)throws Exception;

    int deleteUser(@Param("userId")int userId)throws Exception;
}
