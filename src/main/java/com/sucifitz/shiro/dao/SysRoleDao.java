package com.sucifitz.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sucifitz.shiro.entity.SysRoleEntity;

import java.util.List;

/**
 * 角色Dao
 * @author szh 2020/1/21
 */
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
     * @Author: szh
     * @Description: 通过用户ID查询角色集合
     * @Date: 2020/1/21 15:18
     * @Param: [userId] 用户id
     * @Return: java.util.List<com.sucifitz.shiro.entity.SysRoleEntity>
     **/
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
}
