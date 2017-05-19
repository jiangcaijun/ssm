package com.ssm.shiro;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @description：shiro权限认证
 * 通过UserShiroRealm 继承Shiro验证用户登录的类为自定义的AuthorizingRealm 
 */
public class UserShiroRealm extends AuthorizingRealm {

    private static Logger LOGGER = LoggerFactory.getLogger(UserShiroRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 统一用户登录入口
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- 
     * shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     * 
     * 其中密码加密方式：md5 32为加密
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        LOGGER.info("Shiro开始登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.findUserByUserName(token.getUsername());
        // 账号不存在
        if (user == null) {
            return null;
        }
        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getUserName());
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), getName());
    }
    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        Set<String> urlSet = new HashSet<String>();
            List<Map<String, String>> roleResourceList = new ArrayList<>();
            if (roleResourceList.size() >0) {
                for (Map<String, String> map : roleResourceList) {
                	if(map != null){
                		urlSet.add(map.get("url"));
                	}
                }
            }
        urlSet.add("/manage/user");
        urlSet.add("/manage/user/user");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        return info;

    }
    
    /**
     * 清除权限的缓存,但该方法执行后，前端页面的刷新会略有延迟，这是缓存造成的
     * 
     * @author jiangCaiJun
     */
    public void updateAuthz(Long roleId){
        Set<String> urlSet = new HashSet<String>();
        //此处
        List<Map<String, String>>  roleResourceList= new ArrayList<>();
        if (roleResourceList.size() >0) {
            for (Map<String, String> map : roleResourceList) {
            	if(map != null){
            		urlSet.add(map.get("user"));
            	}
            }
        }
        urlSet.add("/manage/user");
        urlSet.add("/manage/user/user");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
//    	this.clearCachedAuthorizationInfo(null);
    }
    
}
