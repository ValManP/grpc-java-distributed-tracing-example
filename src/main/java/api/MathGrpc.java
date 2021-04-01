package api;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.1)",
    comments = "Source: services.proto")
public final class MathGrpc {

  private MathGrpc() {}

  public static final String SERVICE_NAME = "api.Math";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<api.SqrRequest,
      api.SqrResponse> getSqrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Sqr",
      requestType = api.SqrRequest.class,
      responseType = api.SqrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<api.SqrRequest,
      api.SqrResponse> getSqrMethod() {
    io.grpc.MethodDescriptor<api.SqrRequest, api.SqrResponse> getSqrMethod;
    if ((getSqrMethod = MathGrpc.getSqrMethod) == null) {
      synchronized (MathGrpc.class) {
        if ((getSqrMethod = MathGrpc.getSqrMethod) == null) {
          MathGrpc.getSqrMethod = getSqrMethod =
              io.grpc.MethodDescriptor.<api.SqrRequest, api.SqrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Sqr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.SqrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.SqrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MathMethodDescriptorSupplier("Sqr"))
              .build();
        }
      }
    }
    return getSqrMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MathStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathStub>() {
        @java.lang.Override
        public MathStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathStub(channel, callOptions);
        }
      };
    return MathStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MathBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathBlockingStub>() {
        @java.lang.Override
        public MathBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathBlockingStub(channel, callOptions);
        }
      };
    return MathBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MathFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathFutureStub>() {
        @java.lang.Override
        public MathFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathFutureStub(channel, callOptions);
        }
      };
    return MathFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MathImplBase implements io.grpc.BindableService {

    /**
     */
    public void sqr(api.SqrRequest request,
        io.grpc.stub.StreamObserver<api.SqrResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSqrMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSqrMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                api.SqrRequest,
                api.SqrResponse>(
                  this, METHODID_SQR)))
          .build();
    }
  }

  /**
   */
  public static final class MathStub extends io.grpc.stub.AbstractAsyncStub<MathStub> {
    private MathStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathStub(channel, callOptions);
    }

    /**
     */
    public void sqr(api.SqrRequest request,
        io.grpc.stub.StreamObserver<api.SqrResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSqrMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MathBlockingStub extends io.grpc.stub.AbstractBlockingStub<MathBlockingStub> {
    private MathBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathBlockingStub(channel, callOptions);
    }

    /**
     */
    public api.SqrResponse sqr(api.SqrRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSqrMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MathFutureStub extends io.grpc.stub.AbstractFutureStub<MathFutureStub> {
    private MathFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<api.SqrResponse> sqr(
        api.SqrRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSqrMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SQR = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MathImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MathImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SQR:
          serviceImpl.sqr((api.SqrRequest) request,
              (io.grpc.stub.StreamObserver<api.SqrResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MathBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MathBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return api.Services.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Math");
    }
  }

  private static final class MathFileDescriptorSupplier
      extends MathBaseDescriptorSupplier {
    MathFileDescriptorSupplier() {}
  }

  private static final class MathMethodDescriptorSupplier
      extends MathBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MathMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MathGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MathFileDescriptorSupplier())
              .addMethod(getSqrMethod())
              .build();
        }
      }
    }
    return result;
  }
}
