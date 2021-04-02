package circle;

import api.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import io.opencensus.common.Scope;
import io.opencensus.exporter.trace.jaeger.JaegerExporterConfiguration;
import io.opencensus.exporter.trace.jaeger.JaegerTraceExporter;
import io.opencensus.trace.*;
import io.opencensus.trace.config.TraceConfig;
import io.opencensus.trace.samplers.Samplers;
import io.opentracing.contrib.grpc.TracingClientInterceptor;
import io.opentracing.contrib.grpc.TracingServerInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Circle {
    private static final Tracer tracer = Tracing.getTracer();

    private final int serverPort;
    private Server server;

    public Circle(int port) {
        this.serverPort = port;
    }

    static class CircleImpl extends CircleGrpc.CircleImplBase {
        @Override
        public void area(AreaRequest request, StreamObserver<AreaResponse> responseObserver) {
            SpanBuilder spanBuilder =
                    tracer.spanBuilder("Circle.Area").setRecordEvents(true);

            try (Scope scope = spanBuilder.startScopedSpan()) {
                Span span = tracer.getCurrentSpan();
                span.putAttribute("radius", AttributeValue.doubleAttributeValue(request.getRadius()));
                span.putAttribute("lang", AttributeValue.stringAttributeValue("java"));

                System.out.printf("calls Circle.Area with radius = %f\n", request.getRadius());
                double sqrRadius = callSqr(request.getRadius());
                double area = Math.PI * sqrRadius;

                AreaResponse resp = AreaResponse.newBuilder().setArea(area).build();
                responseObserver.onNext(resp);
                responseObserver.onCompleted();
            }
        }

        public double callSqr(double radius) {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                    .usePlaintext()
                    .intercept(TracingClientInterceptor.newBuilder().build())
                    .build();
            MathGrpc.MathBlockingStub stub = MathGrpc.newBlockingStub(channel);

            SqrRequest req = SqrRequest.newBuilder().setValue(radius).build();
            SqrResponse resp = stub.sqr(req);
            return resp.getResult();
        }
    }


    public void listenAndServe() throws IOException, InterruptedException {
        this.start();
        this.server.awaitTermination();
    }

    private void start() throws IOException {
        this.server = ServerBuilder.forPort(this.serverPort)
                .addService(new CircleImpl())
                .intercept(TracingServerInterceptor.newBuilder().build())
                .build()
                .start();

        Server theServer = this.server;
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread() {
                            public void run() {
                                theServer.shutdown();
                            }
                        });
    }

    public void initExporter() {
        JaegerTraceExporter.createAndRegister(JaegerExporterConfiguration.builder()
                .setThriftEndpoint("http://localhost:14268/api/traces")
                .setServiceName("circle-service").build());

        TraceConfig traceConfig = Tracing.getTraceConfig();
        traceConfig.updateActiveTraceParams(
                traceConfig.getActiveTraceParams()
                        .toBuilder()
                        .setSampler(Samplers.alwaysSample())
                        .build());
    }

    public static void main(String ...args) {
        System.out.println("Circle Service started...");
        Circle server = new Circle(50051);

        server.initExporter();

        try {
            server.listenAndServe();
        } catch (IOException e) {
            System.err.println("Exception encountered while serving: " + e);
        } catch (InterruptedException e) {
            System.err.println("Caught an interrupt: " + e);
        } catch (Exception e) {
            System.err.println("Unhandled exception: " + e);
        }
    }
}
