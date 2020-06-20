package com.orchid.counselling.mapper;


import com.orchid.counselling.pojo.User;
import com.orchid.counselling.vo.ParaVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {


    public User selectUserById(long userId);

    /**
     * 根据用户名查询用户是否为咨询师
     * @param username
     * @return
     */
    public Integer selUserRole(String username);


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User selectUserByName(String username);

    /**
     *根据用户名查询用户id
     * @param username
     * @return
     */
    public Long selUserIdByName(String username);

    /**
     *插入用户数据并返回主键值
     * @param user
     * @return
     */
    public Integer insertUser(User user);

    /**
     *批量新增多个用户
     * @param users
     * @return
     */
    public Integer insertMoreUser(List<User> users);

    /**
     * 根据用户id查询用户信息
     * @param username 用户名
     * @return
     */
    public User selectAllInfoByUsername(String username);

    /**
     * 查询登录用户信息
     * @param user
     * @return
     */
    public Integer updateInfo(User user);

    /**
     * 根据登录管理员用户名查询创建的账户信息
     * @param paraVo 管理员用户名
     * @return
     */
    public List<User> selectUserByAdmin(ParaVo paraVo);

    /**
     * 查询心理咨询师的信息
     * @param username
     * @return
     */
    public List<User> selCounselorByGroup(String username);

    /**
     * 根据用户id删除用户信息
     * @param userId
     * @return
     */
    public Integer delUserById(Long userId);

}
