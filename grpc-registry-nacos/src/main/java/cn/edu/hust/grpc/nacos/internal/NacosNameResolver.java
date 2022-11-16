package cn.edu.hust.grpc.nacos.internal;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 *
 * @Author wylu
 * @Date 2022/11/15 上午 08:34
 */
public class NacosNameResolver extends NameResolver {

    private final Logger logger = LoggerFactory.getLogger(NacosNameResolver.class);

    private final String serviceName;
    private final Properties properties;
    private final NamingService namingService;

    public NacosNameResolver(Properties properties, URI targetUri, NamingService namingService) {
        this.properties = properties;
        // 此处服务名需正确
        this.serviceName = targetUri.getAuthority();
        this.namingService = namingService;
    }

    @Override
    public String getServiceAuthority() {
        return serviceName;
    }

    @Override
    public void start(Listener listener) {
        update(listener);
    }

    @Override
    public void shutdown() {

    }

    private void update(Listener listener) {
        try {
            List<Instance> instances = namingService.getAllInstances(serviceName);
            List<EquivalentAddressGroup> equivalentAddressGroupList = instances.stream()
                    .map(instance ->
                            new EquivalentAddressGroup(new InetSocketAddress(instance.getIp(), instance.getPort())))
                    .collect(Collectors.toList());
            listener.onAddresses(equivalentAddressGroupList, Attributes.EMPTY);
        } catch (NacosException e) {
            logger.error(e.getErrMsg());
        }
    }
}
