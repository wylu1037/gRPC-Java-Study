package cn.edu.hust.grpc.server.service;

import cn.edu.hust.grpc.api.user.IInvokeJavaGrpc;
import cn.edu.hust.grpc.api.user.InvokeJava.InvokeRequest;
import cn.edu.hust.grpc.api.user.InvokeJava.InvokeResponse;
import cn.edu.hust.grpc.server.annotation.GrpcService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class InvokeService extends IInvokeJavaGrpc.IInvokeJavaImplBase {
	private final Logger logger = LoggerFactory.getLogger(InvokeService.class);

	@Override
	public void invokeCrossLanguage(InvokeRequest request, StreamObserver<InvokeResponse> responseObserver) {
		logger.info("InvokeService 收到参数，identity = {}, target = {}", request.getIdentity(), request.getTarget());

		InvokeResponse response = InvokeResponse.newBuilder()
			.setCode(200)
			.setResult(String.format("identity = %s, target = %s", request.getIdentity(), request.getTarget()))
			.build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
