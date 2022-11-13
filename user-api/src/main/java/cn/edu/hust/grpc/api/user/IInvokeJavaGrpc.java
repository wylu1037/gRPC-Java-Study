package cn.edu.hust.grpc.api.user;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: InvokeJava.proto")
public final class IInvokeJavaGrpc {

  private IInvokeJavaGrpc() {}

  public static final String SERVICE_NAME = "invoke.IInvokeJava";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<InvokeJava.InvokeRequest,
      InvokeJava.InvokeResponse> METHOD_INVOKE_CROSS_LANGUAGE =
      io.grpc.MethodDescriptor.<InvokeJava.InvokeRequest, InvokeJava.InvokeResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "invoke.IInvokeJava", "invokeCrossLanguage"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              InvokeJava.InvokeRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              InvokeJava.InvokeResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IInvokeJavaStub newStub(io.grpc.Channel channel) {
    return new IInvokeJavaStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IInvokeJavaBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new IInvokeJavaBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IInvokeJavaFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new IInvokeJavaFutureStub(channel);
  }

  /**
   */
  public static abstract class IInvokeJavaImplBase implements io.grpc.BindableService {

    /**
     */
    public void invokeCrossLanguage(InvokeJava.InvokeRequest request,
        io.grpc.stub.StreamObserver<InvokeJava.InvokeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_INVOKE_CROSS_LANGUAGE, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_INVOKE_CROSS_LANGUAGE,
            asyncUnaryCall(
              new MethodHandlers<
                InvokeJava.InvokeRequest,
                InvokeJava.InvokeResponse>(
                  this, METHODID_INVOKE_CROSS_LANGUAGE)))
          .build();
    }
  }

  /**
   */
  public static final class IInvokeJavaStub extends io.grpc.stub.AbstractStub<IInvokeJavaStub> {
    private IInvokeJavaStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IInvokeJavaStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected IInvokeJavaStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IInvokeJavaStub(channel, callOptions);
    }

    /**
     */
    public void invokeCrossLanguage(InvokeJava.InvokeRequest request,
        io.grpc.stub.StreamObserver<InvokeJava.InvokeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INVOKE_CROSS_LANGUAGE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class IInvokeJavaBlockingStub extends io.grpc.stub.AbstractStub<IInvokeJavaBlockingStub> {
    private IInvokeJavaBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IInvokeJavaBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected IInvokeJavaBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IInvokeJavaBlockingStub(channel, callOptions);
    }

    /**
     */
    public InvokeJava.InvokeResponse invokeCrossLanguage(InvokeJava.InvokeRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INVOKE_CROSS_LANGUAGE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class IInvokeJavaFutureStub extends io.grpc.stub.AbstractStub<IInvokeJavaFutureStub> {
    private IInvokeJavaFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IInvokeJavaFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected IInvokeJavaFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IInvokeJavaFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<InvokeJava.InvokeResponse> invokeCrossLanguage(
        InvokeJava.InvokeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INVOKE_CROSS_LANGUAGE, getCallOptions()), request);
    }
  }

  private static final int METHODID_INVOKE_CROSS_LANGUAGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final IInvokeJavaImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(IInvokeJavaImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INVOKE_CROSS_LANGUAGE:
          serviceImpl.invokeCrossLanguage((InvokeJava.InvokeRequest) request,
              (io.grpc.stub.StreamObserver<InvokeJava.InvokeResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class IInvokeJavaDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return InvokeJava.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (IInvokeJavaGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IInvokeJavaDescriptorSupplier())
              .addMethod(METHOD_INVOKE_CROSS_LANGUAGE)
              .build();
        }
      }
    }
    return result;
  }
}
