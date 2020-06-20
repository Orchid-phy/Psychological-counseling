package com.orchid.counselling.config;

import com.orchid.counselling.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        //加密对象
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //加密方法
        matcher.setHashAlgorithmName("md5");
        //加密次数
        matcher.setHashIterations(1);

        return matcher;
    }

    /**
     * 自定义realm
     * @return
     */
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return myRealm;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        //创建安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置myRealm
        securityManager.setRealm(myRealm());

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //配置拦截器
        Map<String, String> filterChainDefinnitionMap = new HashMap<>();
        //配置不会被拦截的链接
        filterChainDefinnitionMap.put("/img/**","anon");
        filterChainDefinnitionMap.put("/css/**","anon");
        filterChainDefinnitionMap.put("/js/**","anon");
        filterChainDefinnitionMap.put("/fonts/**","anon");
        filterChainDefinnitionMap.put("/dist/**","anon");
        filterChainDefinnitionMap.put("/home/**","anon");
//        filterChainDefinnitionMap.put("/self/**","anon");
        filterChainDefinnitionMap.put("/home","anon");
        //配置退出过滤器
        filterChainDefinnitionMap.put("/logout","logout");

        filterChainDefinnitionMap.put("/user/login_page","anon");
        filterChainDefinnitionMap.put("/user/register_page","anon");
        filterChainDefinnitionMap.put("/user/login","anon");
        filterChainDefinnitionMap.put("/user/register","anon");
//        filterChainDefinnitionMap.put("/**","authc");

        shiroFilterFactoryBean.setLoginUrl("/home");
        shiroFilterFactoryBean.setSuccessUrl("/home");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinnitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 管理shiroBean的生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){

        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

}
