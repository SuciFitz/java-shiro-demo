package com.sucifitz.shiro.common.shiro;

import com.sucifitz.shiro.common.util.ShiroUtil;
import com.sucifitz.shiro.entity.SysMenuEntity;
import com.sucifitz.shiro.entity.SysRoleEntity;
import com.sucifitz.shiro.entity.SysUserEntity;
import com.sucifitz.shiro.service.SysMenuService;
import com.sucifitz.shiro.service.SysRoleService;
import com.sucifitz.shiro.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro权限匹配和账号密码匹配
 * @author szh 2020/4/3
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * @Author: szh
     * @Description: 授权
     * 用户进行权限验证时候Shiro会去缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
     * @Date: 2020/4/3 10:28
     * @Param: [principals]
     * @Return: org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUserEntity sysUserEntity = (SysUserEntity) principals.getPrimaryPrincipal();
        // 获取用户id
        Long userId = sysUserEntity.getUserId();
        // 这里可以进行授权
        Set<String> roleSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        // 查询角色和权限(根据业务自行查询)
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.selectSysRoleByUserId(userId);
        for (SysRoleEntity sysRoleEntity : sysRoleEntityList) {
            roleSet.add(sysRoleEntity.getRoleName());
            List<SysMenuEntity> sysMenuEntityList = sysMenuService.selectSysMenuByRoleId(sysRoleEntity.getRoleId());
            for (SysMenuEntity sysMenuEntity : sysMenuEntityList) {
                permsSet.add(sysMenuEntity.getPerms());
            }
        }
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(roleSet);
        return authorizationInfo;
    }

    /**
     * @Author: szh
     * @Description: 身份认证
     * @Date: 2020/4/3 11:02
     * @Param: [token]
     * @Return: org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户输入账号
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到进行验证
        //实际项目中,这里可以根据实际情况做缓存,如果不做,Shiro自己也是有时间间隔机制,2分钟内不会重复执行该方法
        SysUserEntity userEntity = sysUserService.selectUserByName(username);
        if (userEntity == null) {
            throw new AuthenticationException();
        }
        if (userEntity.getState() == null || "PROHIBIT".equals(userEntity.getState())) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名
                userEntity,
                // 密码
                userEntity.getPassword(),
                // 盐值
                ByteSource.Util.bytes(userEntity.getSalt()),
                getName()
        );
        ShiroUtil.deleteCache(username, true);
        return authenticationInfo;
    }
}