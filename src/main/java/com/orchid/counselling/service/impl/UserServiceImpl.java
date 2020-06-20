package com.orchid.counselling.service.impl;


import com.github.pagehelper.PageHelper;
import com.orchid.counselling.mapper.UserMapper;
import com.orchid.counselling.pojo.Role;
import com.orchid.counselling.pojo.User;
import com.orchid.counselling.service.UserService;
import com.orchid.counselling.util.ImportExcel;
import com.orchid.counselling.util.JsonResult;
import com.orchid.counselling.util.ObjectChange;
import com.orchid.counselling.vo.ParaVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String username) {

        //根据用户名查询用户密码，加密盐值
        User loginUser = userMapper.selectUserByName(username);

        return loginUser;
    }

    @Override
    public Boolean findUserIsCounselor() {
        //获取登录用户的用户信息
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //查询登录用户是否为咨询师
        Integer isCounselor = userMapper.selUserRole(username);
        //判断用户的角色信息
        if(isCounselor > 0){
            return true;
        }

        return false;
    }

    @Override
    public Boolean addUser(User user, String isConsultant) {

        if (("").equals(user.getUsername())
            || ("").equals(user.getRealName())
            || ("").equals(user.getPhone())
            || ("").equals(user.getPassword())
            || ("").equals(user.getSalt()))
        {
            return false;
        }

        //判断用户的角色
        Role role = new Role();
        if (isConsultant != null){
            role.setRoleName("%"+isConsultant+"%");
        }
        user.setRole(role);

        String salt = user.getPassword().substring(1,6);

        //对密码进行加盐加密处理
        Md5Hash md5HashPwd = new Md5Hash(user.getPassword(), salt);
        //重新设置用户密码
        user.setPassword(md5HashPwd.toString());

        //保存盐值
        user.setSalt(salt);

        //添加用户信息
        Integer in = userMapper.insertUser(user);

        if (in < 1){
            return false;
        }

        return true;
    }

    @Override
    public String batchAdd(MultipartFile file) {

        //读取excel中的信息
        String name = file.getOriginalFilename();
        Map<String, Object> map = ImportExcel.uploadExcel(file, name);
        List<User> users = null;
        users = ObjectChange.castList(map.get("userList"), User.class);
        //获取错误信息
        String errorInfo = map.get("errorInfo").toString();

        if (errorInfo != null && !"".equals(errorInfo) ){
            return errorInfo;
        }

        Integer integer = userMapper.insertMoreUser(users);



        if (integer > 0){
            return "success";
        }

        return "error";
    }

    @Override
    public User findUserInfoByUsername() {

        //获取登录用户的用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //根据用户名查询用户的信息
        User user = userMapper.selectAllInfoByUsername(username);

        return user;
    }

    @Override
    public Boolean upUserInfo(User user, String validCode) {

        Integer isUp = userMapper.updateInfo(user);

        if (isUp > 0){
            return true;
        }

        return false;
    }

    @Override
    public List<User> findUserByAdmin(Long userId, String username) {

        //获取登录的管理员的用户名
        String adminUsername = (String) SecurityUtils.getSubject().getPrincipal();

        //查询参数
        ParaVo paraVo = new ParaVo();

        User user = new User();
        if (userId != null && userId != 0){
            user.setUserId(userId);
        }

        if (username != null && !"".equals(username)){
            user.setUsername("%"+username+"%");
        }

        paraVo.setUser(user);
        paraVo.setPara(adminUsername);

        List<User> userList = userMapper.selectUserByAdmin(paraVo);

        return userList;
    }

    @Override
    public List<User> findCounselorByGroup() {

        String username = (String) SecurityUtils.getSubject().getPrincipal();

        List<User> userList = userMapper.selCounselorByGroup(username);

        return userList;
    }

    @Override
    public List<User> findAllCounselor() {

        List<User> userList = userMapper.selCounselorByGroup(null);

        return userList;
    }

    @Override
    public Boolean delUserById(Long userId) {

        Integer userById = userMapper.delUserById(userId);

        if (userById > 0){
            return true;
        }

        return false;
    }
}
