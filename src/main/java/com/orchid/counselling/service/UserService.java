package com.orchid.counselling.service;


import com.orchid.counselling.pojo.User;
import com.orchid.counselling.util.JsonResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {


    /**
     * 登录
     * @param username
     * @return
     */
    public User login(String username);

    /**
     * 判断登录的用户是否是咨询师
     * @return
     */
    public Boolean findUserIsCounselor();

    /**
     * 添加用户
     * @param user 用户信息
     * @return
     */
    public Boolean addUser(User user, String isConsultant);

    /**
     * 批量添加用户，默认密码为1234
     * @param file
     * @return
     */
    public String batchAdd(MultipartFile file);

    /**
     * 查询登录用户信息
     * @return
     */
    public User findUserInfoByUsername();

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public Boolean upUserInfo(User user, String validCode);

    /**
     * 根据登录管理员用户名查询创建的账户信息
     * @return
     */
    public List<User> findUserByAdmin(Long userId, String username);

    /**
     * 根据团队查询心理咨询师的信息
     * @return
     */
    public List<User> findCounselorByGroup();

    /**
     * 查询心理咨询师的信息
     * @return
     */
    public List<User> findAllCounselor();

    /**
     * 根据用户id删除用户信息
     * @param userId
     * @return
     */
    public Boolean delUserById(Long userId);

}
