package cn.edu.hust.grpc.server;

import cn.edu.hust.grpc.nacos.GrpcServer;
import cn.edu.hust.grpc.nacos.util.NacosUtils;
import io.grpc.BindableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * @Author wylu
 * @Date 2022/11/8 上午 09:24
 */
@Component
public class UserServiceManager {

    private GrpcServer server;
    private final int port = 8888; // grpc server port

    private final Logger logger = LoggerFactory.getLogger(UserServiceManager.class);

    private List<BindableService> services = new ArrayList<>();


    /**
     * 加载grpc service
     *
     * @param serviceMap
     */
    void loadService(Map<String, Object> serviceMap) throws IOException, InterruptedException {
        server = new GrpcServer();
        // ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);

        // nacos服务地址
        URI uri = URI.create("http://127.0.0.1:8848");
        Properties properties = new Properties();
        NacosUtils.buildNacosProperties(uri, properties);

        // 添加@GrpcService扫描到的服务
        for (Object bean: serviceMap.values()) {
            BindableService service = (BindableService) bean;
            // serverBuilder.addService(service);
            services.add(service);
            logger.info("{} is register in Spring Boot!!!", bean.getClass().getSimpleName());
        }

        // 启动服务
        // server = serverBuilder.build().start();
        BindableService[] bindableServices = services.toArray(new BindableService[0]);
        server.init(port, properties, bindableServices);
        server.start();
        logger.info("user grpc server is started at port {}", port);

        // 增加一个钩子，当JVM进程退出时关闭server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("====== shutting down grpc since JVM is shutting down!!!");
            if (Objects.nonNull(server)) {
                server.stop();
            }
            logger.info("====== user server shut down!!!");
        }));

        server.server.awaitTermination();
    }
}
