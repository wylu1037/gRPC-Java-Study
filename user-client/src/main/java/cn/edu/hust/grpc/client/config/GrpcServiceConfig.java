package cn.edu.hust.grpc.client.config;

import cn.edu.hust.grpc.api.user.UserServiceGrpc;
import cn.edu.hust.grpc.nacos.internal.NacosNameResolverProvider;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import io.grpc.Attributes;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

/**
 * @Author wylu
 * @Date 2022/11/8 上午 10:31
 */
@Configuration
public class GrpcServiceConfig {

    /**
     * protobuf Message的序列化配置
     *
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return o -> o.serializerByType(Message.class, new JsonSerializer<Message>() {
            @Override
            public void serialize(Message value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeRawValue(new JsonFormat().printToString(value));
            }
        });
    }

    @Bean("managedChannel")
    public ManagedChannel getChannel() {
        // 未使用nacos注册中心
        /*return ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext()
                .build();*/

        // 使用nacos注册中心
        return ManagedChannelBuilder.forTarget("nacos://" + "127.0.0.1:8848")
                .nameResolverFactory(new NacosNameResolverProvider(URI.create("http://127.0.0.1:8848"),
                        Attributes.newBuilder().build()))
                .usePlaintext()
                .build();
    }

    @Bean
    public UserServiceGrpc.UserServiceBlockingStub getUserServiceStub(@Qualifier("managedChannel") ManagedChannel channel) {
        return UserServiceGrpc.newBlockingStub(channel);
    }
}
