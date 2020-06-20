package com.orchid.counselling.mapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PermissionMapperTest {

    @Autowired
    PermissionMapper permissionMapper;

    @Test
    void selectPermsById() {

        Set<String> perms = permissionMapper.selectPermsById(1);

        for (String perm : perms) {
            System.out.println(perm);
        }

    }
}