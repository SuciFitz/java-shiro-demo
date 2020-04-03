package com.sucifitz.shiro.common.util;

import com.sucifitz.shiro.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * @author szh 2020/4/2
 */
public class ShiroUtil {

    /** 私有构造器 **/
    private ShiroUtil(){}

    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

    /**
     * @Author: szh
     * @Description: 获取当前用户session
     * @Date: 2020/4/2 16:59
     * @Return: org.apache.shiro.session.Session
     **/
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * @Author: szh
     * @Description: 用户登出
     * @Date: 2020/4/2 17:02
     * @Return: void
     **/
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * @Author: szh
     * @Description: 获取用户信息
     * @Date: 2020/4/2 17:03
     * @Return: com.sucifitz.shiro.entity.SysUserEntity
     **/
    public static SysUserEntity getUserInfo() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    public static void deleteCache(String username, boolean isRemoveSession) {
        // 从缓存中获取session
        Session session = null;
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        SysUserEntity sysUserEntity;
        Object attribute = null;
        for (Session sessionInfo : sessions) {
            // 遍历Session,找到该用户名称对应的Session
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }
            sysUserEntity = (SysUserEntity) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (sysUserEntity == null) {
                continue;
            }
            if (Objects.equals(sysUserEntity.getUsername(), username)) {
                session = sessionInfo;
                break;
            }
        }
        if (session == null || attribute == null) {
            return;
        }
        // 删除session
        if (isRemoveSession) {
            redisSessionDAO.delete(session);
        }
        // 删除Cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authenticator = securityManager.getAuthenticator();
        ((LogoutAware) authenticator).onLogout((SimplePrincipalCollection) attribute);
    }
}