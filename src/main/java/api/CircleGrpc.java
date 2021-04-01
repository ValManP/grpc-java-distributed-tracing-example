package api;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.1)",
    comments = "Source: services.proto")
public final class CircleGrpc {

  private CircleGrpc() {}

  public static final String SERVICE_NAME = "api.Circle";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<api.AreaRequest,
      api.AreaResponse> getAreaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Area",
      requestType = api.AreaRequest.class,
      responseType = api.AreaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<api.AreaRequest,
      api.AreaResponse> getAreaMethod() {
    io.grpc.MethodDescriptor<api.AreaRequest, api.AreaResponse> getAreaMethod;
    if ((getAreaMethod = CircleGrpc.getAreaMethod) == null) {
      synchronized (CircleGrpc.class) {
        if ((getAreaMethod = CircleGrpc.getAreaMethod) == null) {
          CircleGrpc.getAreaMethod = getAreaMethod =
              io.grpc.MethodDescriptor.<api.AreaRequest, api.AreaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Area"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.AreaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.AreaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CircleMethodDescriptorSupplier("Area"))
              .build();
        }
      }
    }
    return getAreaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CircleStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CircleStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CircleStub>() {
        @java.lang.Override
        public CircleStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CircleStub(channel, callOptions);
        }
      };
    return CircleStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CircleBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CircleBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CircleBlockingStub>() {
        @java.lang.Override
        public CircleBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CircleBlockingStub(channel, callOptions);
        }
      };
    return CircleBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CircleFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CircleFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CircleFutureStub>() {
        @java.lang.Override
        public CircleFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CircleFutureStub(channel, callOptions);
        }
      };
    return CircleFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CircleImplBase implements io.grpc.BindableService {

    /**
     */
    public void area(api.AreaRequest request,
        io.grpc.stub.StreamObserver<api.AreaResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAreaMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAreaMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                api.AreaRequest,
                api.AreaResponse>(
                  this, METHODID_AREA)))
          .build();
    }
  }

  /**
   */
  public static final class CircleStub extends io.grpc.stub.AbstractAsyncStub<CircleStub> {
    private CircleStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CircleStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CircleStub(channel, callOptions);
    }

    /**
     */
    public void area(api.AreaRequest request,
        io.grpc.stub.StreamObserver<api.AreaResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAreaMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CircleBlockingStub extends io.grpc.stub.AbstractBlockingStub<CircleBlockingStub> {
    private CircleBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CircleBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CircleBlockingStub(channel, callOptions);
    }

    /**
     */
    public api.AreaResponse area(api.AreaRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAreaMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CircleFutureStub extends io.grpc.stub.AbstractFutureStub<CircleFutureStub> {
    private CircleFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CircleFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CircleFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<api.AreaResponse> area(
        api.AreaRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAreaMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AREA = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CircleImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CircleImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AREA:
          serviceImpl.area((api.AreaRequest) request,
              (io.grpc.stub.StreamObserver<api.AreaResponse>) responseObserver);
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

  private static abstract class CircleBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CircleBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return api.Services.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Circle");
    }
  }

  private static final class CircleFileDescriptorSupplier
      extends CircleBaseDescriptorSupplier {
    CircleFileDescriptorSupplier() {}
  }

  private static final class CircleMethodDescriptorSupplier
      extends CircleBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CircleMethodDescriptorSupplier(String methodName) {
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
      synchronized (CircleGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CircleFileDescriptorSupplier())
              .addMethod(getAreaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
