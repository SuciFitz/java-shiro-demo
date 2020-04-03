package com.sucifitz.shiro.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author szh 2020/4/3
 */
@RestController
@RequestMapping("/role")
public class UserRoleController {

    /**
     * @Author: szh
     * @Description: 管理员角色测试接口
     * @Date: 2020/4/3 13:19
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getAdminInfo")
    @RequiresRoles("ADMIN")
    public Map<String, Object> getAdminInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "message for admin");
        return map;
    }

    /**
     * @Author: szh
     * @Description: 用户角色测试接口
     * @Date: 2020/4/3 13:30
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getUserInfo")
    @RequiresRoles("USER")
    public Map<String, Object> getUserInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "message for user");
        return map;
    }

    /**
     * @Author: szh
     * @Description: 角色测试接口
     * @Date: 2020/4/3 13:31
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getRoleInfo")
    @RequiresRoles(value = {"USER", "ADMIN"}, logical = Logical.OR)
    public Map<String, Object> getRoleInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "message for user and admin");
        return map;
    }

    /**
     * @Author: szh
     * @Description: 登出测试
     * @Date: 2020/4/3 13:32
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getLogout")
    @RequiresUser
    public Map<String, Object> getLogout() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "logout");
        return map;
    }

}