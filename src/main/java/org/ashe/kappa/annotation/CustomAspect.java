package org.ashe.kappa.annotation;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashe.kappa.auth.model.Role;
import org.ashe.kappa.auth.service.JwtService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义注解统一切面
 * <p></p>
 * auth模块，所用自定义注解的切面逻辑统一写入此类
 */
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class CustomAspect {

    private final JwtService jwtService;

    /*========================================= @Auth ==========================================*/

    /**
     * 权限注解切面逻辑实现
     */
    @Around("@annotation(org.ashe.kappa.annotation.Auth)")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        String role = jwtService.extractRole();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        Auth annotation = method.getAnnotation(Auth.class);
        Assert.notNull(annotation, "Missing @Auth Annotation on target method");

        Role requiredRole = annotation.value();
        Assert.state(requiredRole.name().equals(role), String.format("Only %s can request", requiredRole));

        return joinPoint.proceed();
    }



}
