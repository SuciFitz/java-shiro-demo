package com.sucifitz.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sucifitz.shiro.entity.SysUserEntity;

/**
 * @author szh 2020/1/21
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * @Author: szh
     * @Description: 根据用户名查询用户实体
     * @Date: 2020/1/21 16:00
     * @Param: [username]
     * @Return: com.sucifitz.shiro.entity.SysUserEntity
     **/
    SysUserEntity selectUserByName(String username);
}
