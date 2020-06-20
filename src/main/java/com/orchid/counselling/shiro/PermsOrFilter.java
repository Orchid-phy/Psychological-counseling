package com.orchid.counselling.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PermsOrFilter extends AuthorizationFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 得到权限数组
        String[] perms = (String[]) o;
        // 判断权限是否为空
        if(perms == null || perms.length == 0){
            return true;
        }
        // or 的逻辑处理
        for (String perm : perms) {
            // 利用主体去判断是否有对应的权限
            if (subject.isPermitted(perm)) {
                return true;
            }
        }
        // 返回false 表示没有权限
        return false;
    }
}
