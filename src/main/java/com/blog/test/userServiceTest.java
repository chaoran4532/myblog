package com.blog.test;

import com.blog.service.CategoryService;
import com.blog.service.UserService;
import com.blog.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class userServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;


}
