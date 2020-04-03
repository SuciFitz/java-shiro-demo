package com.sucifitz.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucifitz.shiro.dao.SysMenuDao;
import com.sucifitz.shiro.entity.SysMenuEntity;
import com.sucifitz.shiro.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author szh 2020/1/21
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    /**
     * @Author: szh
     * @Description: 根据角色查询用户权限
     * @Date: 2020/1/21 16:17
     * @Param: [roleId]
     * @Return: java.util.List<com.sucifitz.shiro.entity.SysMenuEntity>
     **/
    @Override
    public List<SysMenuEntity> selectSysMenuByRoleId(Long roleId) {
        return this.baseMapper.selectSysMenuByRoleId(roleId);
    }
}