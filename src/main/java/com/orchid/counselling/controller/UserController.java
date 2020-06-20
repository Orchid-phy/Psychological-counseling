package com.orchid.counselling.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.pojo.User;
import com.orchid.counselling.service.UserService;
import com.orchid.counselling.util.JsonResult;
import com.orchid.counselling.vo.ParaVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //管理员用户查询参数，用户id
    private static Long in_searchId;

    //查询用户，用户名
    private static String in_searchUsername;

    /**
     * 登陆页面
     *
     * @return
     */
    @GetMapping("/login_page")
    public String loginPage() {
        return "login";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping("/register_page")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, String isConsultant) {

        Boolean isAdd = userService.addUser(user, isConsultant);

        if (isAdd) {
            return "login";
        }

        return "register";
    }

    /**
     * 登陆的方法
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String login(User user) {
        // 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 准备token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 设置记住我
//        token.setRememberMe(user.isRememberMe());
        // 登陆
        try {
            subject.login(token);

            return "redirect:/home/home.html";
        } catch (Exception e) {

            return "login";
        }

    }

    //test
    @GetMapping("/getUser")
    @ResponseBody
    public JsonResult getUser(String username){

        User info = userService.login(username);

        return new JsonResult(200, "success", info);
    }

    /**
     * 查询登录用户名查询用户详细信息
     *
     * @return
     */
    @GetMapping("/getUserInfoByUsername")
    @ResponseBody
    public User getUserInfoByUsername() {

        return userService.findUserInfoByUsername();

    }

    @PostMapping("/upUserInfo")
    @ResponseBody
    public JsonResult updateUserInfo(@Validated @RequestBody User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new JsonResult(5001, bindingResult.getFieldError().getDefaultMessage());
        }

        //更新用户信息
        Boolean isUp = userService.upUserInfo(user, user.getValidCode());

        if (isUp) {
            return new JsonResult(200, "success");
        }

        return new JsonResult(400, "更新失败，请重新更新。");
    }

    @GetMapping("/getUserByAdmin")
    @ResponseBody
    public JsonResult getUserByAdmin(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                     @RequestParam(defaultValue = "0", value = "searchUserId", required = false) Long searchId,
                                     @RequestParam(defaultValue = "", value = "searchUsername", required = false) String searchUsername) {

        if (!"".equals(searchId)) {
            in_searchId = searchId;
        }

        if (!"".equals(searchUsername)) {
            in_searchUsername = searchUsername;
        }

        Map<String, Object> userMap = new HashMap<>();
        if (in_searchId == 0) {
            userMap.put("searchUserId", "");
        } else {
            userMap.put("searchUserId", in_searchId);
        }
        userMap.put("searchUsername", in_searchUsername);

        PageHelper.startPage(pageNum, 4);
        //查询数据
        List<User> userList = userService.findUserByAdmin(in_searchId, in_searchUsername);

        //将查询到的信息保存
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        userMap.put("pageInfo", pageInfo);

        if (userList.size() == 0) {
            return new JsonResult(4001, "未查询到数据");
        }

        return new JsonResult(200, "success", userMap);
    }

    /**
     * 根据用户id删除用户
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/delUser")
    @ResponseBody
    public JsonResult delUserById(Long userId) {

        Boolean userById = userService.delUserById(userId);

        if (userById) {
            return new JsonResult(200, "success");
        }

        return new JsonResult(4001, "删除失败");
    }

    /**
     * 查询该团队的心理咨询师的信息
     *
     * @return
     */
    @GetMapping("/getCounselorInfo")
    @ResponseBody
    public JsonResult getCounselorInfoByGroup() {

        //查询该团队的成员
        List<User> userList = userService.findCounselorByGroup();

        return new JsonResult(200, "success", userList);
    }

    /**
     * 查询心理咨询师的信息并显示
     * @return
     */
    @GetMapping("/getAllCounselor")
    @ResponseBody
    public JsonResult getAllCounselor(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){

        PageHelper.startPage(pageNum, 18);

        //查询该团队的成员
        List<User> userList = userService.findAllCounselor();
        PageInfo<User> counselorPageInfo = new PageInfo<User>(userList);

        return new JsonResult(200, "success",counselorPageInfo);
    }

    @GetMapping("/getUserIsCounselor")
    @ResponseBody
    public JsonResult getUserIsCounselor(){

        //查询用户是否是咨询师
        Boolean userIsCounselor = userService.findUserIsCounselor();

        if(userIsCounselor){
            return new JsonResult(200, "yes");
        }

        return new JsonResult(400, "no");
    }

    /**
     * 批量导入用户，密码默认为1234
     * @return
     */
    @PostMapping("/batchAddUser")
    @ResponseBody
    public JsonResult batchAddUser(MultipartFile file){

        //读取文件并且批量添加用户信息
        String resultInfo = userService.batchAdd(file);

        if (!"error".equals(resultInfo) && !"success".equals(resultInfo) ){
            return new JsonResult(500, resultInfo);
        }

        if ("success".equals(resultInfo) ){
            return new JsonResult(200, "success");
        }

        return new JsonResult(4001, "error");

    }


}
