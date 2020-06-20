package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void selectUserById() {


//        User user = userMapper.selectUserById(1);

//        User user = userMapper.selectUserByName("orchid");
//
//        System.out.println(user.getUsername());
//
//        System.out.println(user.getRole().getRoleId());

//        User user = new User();
//        user.setUsername("test");
//
//        Integer id = userMapper.insertUser(user);
//
//        System.out.println(id);

        User user = userMapper.selectUserByName("root");

        System.out.println(user.getRole().getRoleName());

    }
}