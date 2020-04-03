package com.sucifitz.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucifitz.shiro.dao.SysRoleMenuDao;
import com.sucifitz.shiro.entity.SysRoleMenuEntity;
import com.sucifitz.shiro.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author szh 2020/1/21
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

}