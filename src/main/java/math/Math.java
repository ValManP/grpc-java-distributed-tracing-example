package math;

import api.MathGrpc;
import api.SqrRequest;
import api.SqrResponse;
import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import io.opencensus.common.Scope;
import io.opencensus.contrib.grpc.metrics.RpcViews;
import io.opencensus.exporter.trace.jaeger.JaegerExporterConfiguration;
import io.opencensus.exporter.trace.jaeger.JaegerTraceExporter;
import io.opencensus.trace.*;
import io.opencensus.trace.config.TraceConfig;
import io.opencensus.trace.samplers.Samplers;
import io.opentracing.contrib.grpc.TracingServerInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Math {
    private static final Tracer tracer = Tracing.getTracer();

    private final int serverPort;
    private Server server;

    public Math(int port) {
        this.serverPort = port;
    }

    static class MathImpl extends MathGrpc.MathImplBase {
        @Override
        public void sqr(SqrRequest request, StreamObserver<SqrResponse> responseObserver) {
            SpanBuilder spanBuilder = tracer.spanBuilder("Math.Sqr").setRecordEvents(true);
            try (Scope scope = spanBuilder.startScopedSpan()) {
                Span span = tracer.getCurrentSpan();

                Map<String, AttributeValue> attributes = new HashMap<String, AttributeValue>();
                attributes.put("value", AttributeValue.doubleAttributeValue(request.getValue()));
                span.addAnnotation("Invoking Math.Sqr", attributes);

                System.out.printf("calls Math.Sqr with value = %f\n", request.getValue());
                SqrResponse resp;

                double value = request.getValue();
                resp = SqrResponse.newBuilder().setResult(value * value).build();

                responseObserver.onNext(resp);
                responseObserver.onCompleted();
            }
        }
    }


    public void listenAndServe() throws IOException, InterruptedException {
        this.start();
        this.server.awaitTermination();
    }

    private void start() throws IOException {
        this.server = ServerBuilder.forPort(this.serverPort)
                .addService(new MathImpl())
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
                .setServiceName("math-service").build());

        TraceConfig traceConfig = Tracing.getTraceConfig();
        traceConfig.updateActiveTraceParams(
                traceConfig.getActiveTraceParams()
                        .toBuilder()
                        .setSampler(Samplers.alwaysSample())
                        .build());
    }

    public static void main(String... args) {
        Math server = new Math(50052);
        server.initExporter();
        System.out.println("Math Service started...");
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
