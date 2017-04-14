package com.ssm.controller;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiangcaijun on 2017/3/29.
 */
@Controller
@RequestMapping("/manage")
public class LoginController extends BaseController{
    private static final Logger LOG = Logger.getLogger(LoginController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * GET 登录
     *
     * @return {String}
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        LOG.info("进入manage/login");
        return "manage/login";
    }

    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    @ResponseBody
    public Object loginPost(String username, String password) {
        if (StringUtils.isBlank(username)) {
            return renderError("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return renderError("密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            logger.error("账号不存在：{}", e);
            return renderError("账号不存在");
        } catch (DisabledAccountException e) {
            logger.error("账号未启用：{}", e);
            return renderError("账号未启用");
        } catch (IncorrectCredentialsException e) {
            logger.error("密码错误：{}", e);
            return renderError("密码错误");
        } catch (ExcessiveAttemptsException e) {
            logger.error("登录失败多次，账户锁定：{}", e);
            return renderError("登录失败多次，账户锁定10分钟");
        } catch (RuntimeException e) {
            logger.error("未知错误,请联系管理员：{}", e);
            return renderError("未知错误,请联系管理员");
        }
        //得到更新信息 end
        User user = userService.findUserByUserId(username);

        return renderSuccess();
    }

    @RequiresPermissions(value = { "/user/userInformation" })
    @RequestMapping(value = "/userInformation", method = RequestMethod.GET)
    public String userInformation(Model model) {
        LOG.info("进入manage/userInformation");
        return "manage/userInformation";
    }
}