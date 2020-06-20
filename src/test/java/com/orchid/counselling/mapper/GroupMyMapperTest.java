package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GroupMyMapperTest {

    @Autowired
    GroupMyMapper groupMyMapper;

    @Test
    void selAllGroupByAdmin() {

        GroupMy groupMy = new GroupMy();

        User user = new User();
        user.setUsername("root");

        groupMy.setAdministrator(user);

//        List<GroupMy> groupMIES = groupMyMapper.selAllGroupByCounselor(groupMy);

//        for (GroupMy g:groupMIES
//             ) {
//            System.out.println(g);
//        }

    }
}