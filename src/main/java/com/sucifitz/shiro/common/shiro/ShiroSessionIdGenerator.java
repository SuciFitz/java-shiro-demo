package com.sucifitz.shiro.common.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * @author szh 2020/4/2
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    /**
     * @Author: szh
     * @Description: 实现SessionId生成
     * @Date: 2020/4/2 17:27
     * @Param: [session]
     * @Return: java.io.Serializable
     **/
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format("login_token_%s", sessionId);
    }
}