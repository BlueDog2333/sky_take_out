package com.sky.aop;

import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class AutoFill {
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void pc(){};
    @Before("pc()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodS= (MethodSignature) joinPoint.getSignature();
        try {
            com.sky.annotation.AutoFill annotation=methodS.getMethod().getAnnotation(com.sky.annotation.AutoFill.class);
            Object[] args=joinPoint.getArgs();
            Object object=args[0];
            if(annotation.value()==OperationType.INSERT){
                Method setCreateUserMethod=object.getClass().getMethod(AutoFillConstant.SET_CREATE_USER,Long.class);
                Method setCreateTimeMethod=object.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateUserMethod=object.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);
                Method setUpdateTimeMethod=object.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
                setCreateUserMethod.invoke(object, BaseContext.getCurrentId());
                setCreateTimeMethod.invoke(object,LocalDateTime.now());
                setUpdateUserMethod.invoke(object,BaseContext.getCurrentId());
                setUpdateTimeMethod.invoke(object,LocalDateTime.now());

            }else{
                Method setUpdateUserMethod=object.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);
                Method setUpdateTimeMethod=object.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
                setUpdateUserMethod.invoke(object,BaseContext.getCurrentId());
                setUpdateTimeMethod.invoke(object,LocalDateTime.now());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
