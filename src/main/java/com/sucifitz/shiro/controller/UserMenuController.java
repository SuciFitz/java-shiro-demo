package com.sucifitz.shiro.controller;

import com.sucifitz.shiro.common.util.ShiroUtil;
import com.sucifitz.shiro.entity.SysMenuEntity;
import com.sucifitz.shiro.entity.SysRoleEntity;
import com.sucifitz.shiro.entity.SysRoleMenuEntity;
import com.sucifitz.shiro.entity.SysUserEntity;
import com.sucifitz.shiro.service.SysMenuService;
import com.sucifitz.shiro.service.SysRoleMenuService;
import com.sucifitz.shiro.service.SysRoleService;
import com.sucifitz.shiro.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author szh 2020/4/3
 */
@RestController
@RequestMapping("/menu")
public class UserMenuController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * @Author: szh
     * @Description: 获取用户信息集合
     * @Date: 2020/4/3 13:37
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getUserInfoList")
    @RequiresPermissions("sys:user:info")
    public Map<String, Object> getUserInfoList() {
        Map<String, Object> map = new HashMap<>(1);
        List<SysUserEntity> sysUserEntityList = sysUserService.list();
        map.put("sysUserEntityList", sysUserEntityList);
        return map;
    }

    /**
     * @Author: szh
     * @Description: 获取角色信息集合
     * @Date: 2020/4/3 13:38
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getRoleInfoList")
    @RequiresPermissions("sys:user:info")
    public Map<String, Object> getRoleInfoList() {
        Map<String, Object> map = new HashMap<>(1);
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.list();
        map.put("sysRoleEntityList", sysRoleEntityList);
        return map;
    }

    /**
     * @Author: szh
     * @Description: 获取权限信息集合
     * @Date: 2020/4/3 13:39
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getMenuInfoList")
    @RequiresPermissions("sys:menu:info")
    public Map<String, Object> getMenuInfoList() {
        Map<String, Object> map = new HashMap<>(1);
        List<SysMenuEntity> sysMenuEntityList = sysMenuService.list();
        map.put("sysMenuEntityList", sysMenuEntityList);
        return map;
    }

    /**
     * @Author: szh
     * @Description:  获取所有数据
     * @Date: 2020/4/3 14:03
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/getInfoAll")
    @RequiresPermissions("sys:info:all")
    public Map<String, Object> getInfoAll() {
        Map<String, Object> map = new HashMap<>(3);
        List<SysUserEntity> sysUserEntityList = sysUserService.list();
        map.put("sysUserEntityList", sysUserEntityList);
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.list();
        map.put("sysRoleEntityList", sysRoleEntityList);
        List<SysMenuEntity> sysMenuEntityList = sysMenuService.list();
        map.put("sysMenuEntityList", sysMenuEntityList);
        return map;
    }

    /**
     * @Author: szh
     * @Description: 添加管理员角色权限(测试动态权限更新)
     * @Date: 2020/4/3 14:09
     * @Param: []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/addMenu")
    public Map<String, Object> addMenu() {
        // 添加管理员角色权限
        SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
        sysRoleMenuEntity.setMenuId(4L);
        sysRoleMenuEntity.setRoleId(1L);
        sysRoleMenuService.save(sysRoleMenuEntity);
        // 清除缓存
        String username = "admin";
        ShiroUtil.deleteCache(username, false);
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", 200);
        map.put("msg", "权限添加成功");
        return map;
    }
}