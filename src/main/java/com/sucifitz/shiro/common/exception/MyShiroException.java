package com.sucifitz.shiro.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常类
 *
 * @author szh 2020/4/2
 */
@ControllerAdvice
public class MyShiroException {

    /**
     * @Author: szh
     * @Description: 处理shiro权限拦截异常  如果返回JSON数据格式请加上 @ResponseBody注解
     * @Date: 2020/4/2 16:48
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     **/
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String, Object> defaultErrorHandler() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("403", "权限不足");
        return map;
    }
}