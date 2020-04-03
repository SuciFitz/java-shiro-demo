package com.sucifitz.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sucifitz.shiro.entity.SysMenuEntity;

import java.util.List;

/**
 * 权限Dao
 * @author szh 2020/1/21
 */
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * @Author: szh
     * @Description: 根据角色查询用户权限
     * @Date: 2020/1/21 13:49
     * @Param: [roleId]
     * @Return: java.util.List<com.sucifitz.shiro.entity.SysMenuEntity>
     **/
    List<SysMenuEntity> selectSysMenuByRoleId(Long roleId);
}
