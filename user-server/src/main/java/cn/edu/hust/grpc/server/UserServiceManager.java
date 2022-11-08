package cn.edu.hust.grpc.server;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @Author wylu
 * @Date 2022/11/8 上午 09:24
 */
@Component
public class UserServiceManager {

    private Server server;
    private final int port = 8888; // grpc server port

    private final Logger logger = LoggerFactory.getLogger(UserServiceManager.class);


    /**
     * 加载grpc service
     *
     * @param serviceMap
     */
    void loadService(Map<String, Object> serviceMap) throws IOException, InterruptedException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);

        // 添加@GrpcService扫描到的服务
        for (Object bean: serviceMap.values()) {
            serverBuilder.addService((BindableService) bean);
            logger.info("{} is register in Spring Boot!!!", bean.getClass().getSimpleName());
        }

        // 启动服务
        server = serverBuilder.build().start();
        logger.info("user grpc server is started at port {}", port);

        // 增加一个钩子，当JVM进程退出时关闭server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("====== shutting down grpc since JVM is shutting down!!!");
            if (Objects.nonNull(server)) {
                server.shutdown();
            }
            logger.info("====== user server shut down!!!");
        }));

        server.awaitTermination();
    }
}
