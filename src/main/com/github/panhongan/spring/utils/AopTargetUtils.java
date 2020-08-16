package com.github.panhongan.spring.utils;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * @author panhongan
 * @since 2020.8.15
 * @version 1.0
 */

public class AopTargetUtils {

    public static Object getTarget(Object object) {
        if (!AopUtils.isAopProxy(object)) {
            return object;
        }

        if (AopUtils.isJdkDynamicProxy(object)) {
            return getJdkDynamicProxyTargetObject(object);
        } else {
            return getCglibProxyTargetObject(object);
        }
    }

    public static Object getCglibProxyTargetObject(Object object) {
        try {
            // h field
            Field field = object.getClass().getSuperclass().getDeclaredField("CGLIB$CALLBACK_0");
            field.setAccessible(true);

            // advised field
            Object aopProxy = field.get(object);
            Field advised = aopProxy.getClass().getDeclaredField("advised");
            advised.setAccessible(true);

            // target
            Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getJdkDynamicProxyTargetObject(Object object) {
        try {
            // h field
            Field field = object.getClass().getSuperclass().getDeclaredField("h");
            field.setAccessible(true);

            // advised field
            AopProxy aopProxy = (AopProxy) field.get(object);
            Field advised = aopProxy.getClass().getDeclaredField("advised");
            advised.setAccessible(true);

            // target
            Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
