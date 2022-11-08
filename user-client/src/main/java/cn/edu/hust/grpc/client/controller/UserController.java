package cn.edu.hust.grpc.client.controller;

import cn.edu.hust.grpc.api.user.UserRequest;
import cn.edu.hust.grpc.api.user.UserResponse;
import cn.edu.hust.grpc.api.user.UserServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author wylu
 * @Date 2022/11/8 上午 10:37
 */
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @GetMapping("query")
    // @GetMapping(value = "query", produces="application/x-protobuf;charset=UTF-8")
    public UserResponse query() {
        UserRequest request = UserRequest.newBuilder()
                .setName("我是客户端")
                .build();

        UserResponse response = userServiceBlockingStub.query(request);
        logger.info("User Client receive data: name = {}, age = {}, address = {}", response.getName(),
                response.getAge(), response.getAddress());
        return response;
    }
}
