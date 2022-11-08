package cn.edu.hust.grpc.server.service;

import cn.edu.hust.grpc.api.user.UserRequest;
import cn.edu.hust.grpc.api.user.UserResponse;
import cn.edu.hust.grpc.api.user.UserServiceGrpc;
import cn.edu.hust.grpc.server.annotation.GrpcService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author wylu
 * @Date 2022/11/8 上午 10:23
 */
@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public void query(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        logger.info("UserService 接收到参数：name = {}", request.getName());

        UserResponse response = UserResponse.newBuilder()
                .setName("中科晶格")
                .setAge(2)
                .setAddress("安徽省合肥市")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
