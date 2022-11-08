package cn.edu.hust.grpc.server.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义注解，用于SpringBoot扫描类
 *
 * @Author wylu
 * @Date 2022/11/8 上午 09:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GrpcService {}
