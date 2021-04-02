package client;

import api.AreaRequest;
import api.AreaResponse;
import api.CircleGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.opencensus.common.Scope;
import io.opencensus.exporter.trace.jaeger.JaegerExporterConfiguration;
import io.opencensus.exporter.trace.jaeger.JaegerTraceExporter;
import io.opencensus.trace.*;
import io.opencensus.trace.config.TraceConfig;
import io.opencensus.trace.samplers.Samplers;
import io.opentracing.contrib.grpc.TracingClientInterceptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
    private static final Tracer tracer = Tracing.getTracer();

    private final ManagedChannel channel;
    private final CircleGrpc.CircleBlockingStub stub;

    public Client(String serverHost, int serverPort) {
        this.channel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
                .usePlaintext()
                .intercept(TracingClientInterceptor.newBuilder().build())
                .build();


        this.stub = CircleGrpc.newBlockingStub(this.channel);
    }

    public double area(double radius) {
        SpanBuilder spanBuilder =
                tracer.spanBuilder("ClientSpan").setRecordEvents(true);
        try (Scope scope = spanBuilder.startScopedSpan()) {
            Span span = tracer.getCurrentSpan();
            span.putAttribute("lang", AttributeValue.stringAttributeValue("java"));

            AreaRequest in = AreaRequest.newBuilder().setRadius(radius).build();
            AreaResponse out = this.stub.area(in);
            return out.getArea();
        }
    }

    public void initExporter() {
        JaegerTraceExporter.createAndRegister(JaegerExporterConfiguration.builder()
                .setThriftEndpoint("http://localhost:14268/api/traces")
                .setServiceName("client-service").build());

        TraceConfig traceConfig = Tracing.getTraceConfig();
        traceConfig.updateActiveTraceParams(
                traceConfig.getActiveTraceParams()
                        .toBuilder()
                        .setSampler(Samplers.alwaysSample())
                        .build());
    }

    public static void main(String... args) throws Exception {
        Client cc = new Client("localhost", 50051);

        cc.initExporter();

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("> ");
            System.out.flush();
            String in = stdin.readLine();
            System.out.println("Call Circle.Area...");

            double out = cc.area(Double.parseDouble(in));
            System.out.printf("Area: %f%n", out);
        }
    }
}
