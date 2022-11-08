package cn.edu.hust.grpc.server;

import cn.edu.hust.grpc.server.annotation.GrpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Map;

/**
 * @Author wylu
 * @Date 2022/11/8 上午 09:18
 */
@SpringBootApplication
public class UserServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 启动SpringBoot web
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(UserServerApplication.class, args);

        Map<String, Object> grpcServiceBeanMap = configurableApplicationContext.getBeansWithAnnotation(GrpcService.class);
        UserServiceManager serviceManager = configurableApplicationContext.getBean(UserServiceManager.class);
        serviceManager.loadService(grpcServiceBeanMap);
    }
}
