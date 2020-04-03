package com.sucifitz.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sucifitz.shiro.entity.SysMenuEntity;

import java.util.List;

/**
 * 权限业务接口
 * @author szh 2020/1/21
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * @Author: szh
     * @Description: 根据角色查询用户权限
     * @Date: 2020/1/21 15:55
     * @Param: [roleId]
     * @Return: java.util.List<com.sucifitz.shiro.entity.SysMenuEntity>
     **/
    List<SysMenuEntity> selectSysMenuByRoleId(Long roleId);
}
