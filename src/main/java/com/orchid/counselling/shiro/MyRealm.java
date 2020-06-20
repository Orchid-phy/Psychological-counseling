package com.orchid.counselling.shiro;

import com.orchid.counselling.pojo.User;
import com.orchid.counselling.service.PermissionService;
import com.orchid.counselling.service.RoleService;
import com.orchid.counselling.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //得到用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();

        //通过用户名查询所有的信息
        User user = userService.login(userName);

        //通过用户去获取角色
        //通过用户去获取权限数据
        Set<String> roles = roleService.findRoleNameById(user.getRole().getRoleId());
        Set<String> permissions = permissionService.findPermsById(user.getRole().getRoleId());

        // 返回授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户名
        String userName = (String) authenticationToken.getPrincipal();

        User user = userService.login(userName);

        //通过用户名获取密码
        String password = user.getPassword();

        if (password == null){
            return null;
        }

        //返回认证信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, "myRealm");

        //加盐
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));

        return info;
    }

}
