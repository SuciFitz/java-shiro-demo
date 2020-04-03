package com.sucifitz.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucifitz.shiro.dao.SysRoleDao;
import com.sucifitz.shiro.entity.SysRoleEntity;
import com.sucifitz.shiro.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author szh 2020/1/22
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return this.baseMapper.selectSysRoleByUserId(userId);
    }
}