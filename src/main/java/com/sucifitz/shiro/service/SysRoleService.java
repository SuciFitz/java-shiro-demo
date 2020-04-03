package com.sucifitz.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sucifitz.shiro.entity.SysRoleEntity;

import java.util.List;

/**
 * 角色业务接口
 * @author szh 2020/1/21
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * @Author: szh
     * @Description: 通过用户ID查询角色权限
     * @Date: 2020/1/21 15:57
     * @Param: [userId]
     * @Return: java.util.List<com.sucifitz.shiro.entity.SysRoleEntity>
     **/
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
}
