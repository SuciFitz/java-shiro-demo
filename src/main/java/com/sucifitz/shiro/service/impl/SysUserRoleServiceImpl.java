package com.sucifitz.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucifitz.shiro.dao.SysUserRoleDao;
import com.sucifitz.shiro.entity.SysUserRoleEntity;
import com.sucifitz.shiro.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author szh 2020/1/22
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

}