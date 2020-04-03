package com.sucifitz.shiro.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author szh 2020/4/2
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * @Author: szh
     * @Description: Spring在bean初始化后会判断是不是ApplicationContextAware的子类
     * 如果该类是,setApplicationContext()方法,会将容器中ApplicationContext作为参数传入进去
     * @Date: 2020/4/2 16:54
     * @Param: [applicationContext]
     * @Return: void
     **/
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}