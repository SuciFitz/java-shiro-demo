package com.sucifitz.shiro.controller;

import com.sucifitz.shiro.common.util.SHA256Util;
import com.sucifitz.shiro.common.util.ShiroUtil;
import com.sucifitz.shiro.entity.SysUserEntity;
import com.sucifitz.shiro.entity.SysUserRoleEntity;
import com.sucifitz.shiro.service.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author szh 2020/4/3
 */
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody SysUserEntity sysUserEntity) {
        Map<String, Object> map = new HashMap<>(3);
        // 进行身份验证
        try {
            // 验证身份和登录
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUserEntity.getUsername(), sysUserEntity.getPassword());
            // 验证成功进行登录操作
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            map.put("code", 500);
            map.put("msg", "用户不存在或密码错误");
        } catch (LockedAccountException e) {
            map.put("code", 500);
            map.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            map.put("code", 500);
            map.put("msg", "该用户不存在");
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "未知异常");
        }
        map.put("code", 0);
        map.put("msg", "登陆成功");
        map.put("token", ShiroUtil.getSession().getId().toString());
        return map;
    }

    /**
     * @Author: szh
     * @Description: 未登录
     * @Date: 2020/4/3 14:21
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/unauth")
    public Map<String, Object> unauth() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "未登录");
        return map;
    }

    /**
     * @Author: szh
     * @Description: 添加一个用户演示接口
     * 这里仅作为演示，不加任何权限和重复查询校验
     * @Date: 2020/4/3 14:31
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/testAddUser")
    public Map<String, Object> testAddUser() {
        // 设置基础参数
        SysUserEntity sysUser = new SysUserEntity();
        sysUser.setUsername("user1");
        sysUser.setState("NORMAL");
        // 生成盐值
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUser.setSalt(salt);
        // 进行加密
        String password = "123456";
        sysUser.setPassword(SHA256Util.sha256(password, sysUser.getSalt()));
        // 保存用户
        sysUserService.save(sysUser);
        // 保存角色
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(sysUser.getUserId());
        sysUserRoleService.save(sysUserRoleEntity);
        // 返回结果
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "添加成功");
        return map;
    }
}