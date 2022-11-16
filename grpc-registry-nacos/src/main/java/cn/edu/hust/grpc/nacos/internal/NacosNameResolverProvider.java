package cn.edu.hust.grpc.nacos.internal;

import cn.edu.hust.grpc.nacos.util.NacosUtils;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Properties;

/**
 * @Author wylu
 * @Date 2022/11/15 上午 08:45
 */
public class NacosNameResolverProvider extends NameResolverProvider {

    private final Logger logger = LoggerFactory.getLogger(NacosNameResolverProvider.class);

    private static final String NACOS = "nacos";

    private final URI uri;

    public NacosNameResolverProvider(URI targetUri, Attributes params) {
        this.uri = targetUri;
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 6;
    }

    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        Properties properties = new Properties();
        return new NacosNameResolver(properties, targetUri, buildNamingService(properties));
    }

    @Override
    public String getDefaultScheme() {
        return NACOS;
    }

    /**
     * 创建NamingService
     *
     * @param properties
     * @return
     */
    private NamingService buildNamingService (Properties properties) {
        NamingService namingService = null;
        NacosUtils.buildNacosProperties(uri, properties);

        try {
            namingService = NacosFactory.createNamingService(properties);
        } catch (NacosException e) {
            logger.error("build naming service error, msg: {}", e.getErrMsg());
        }
        return namingService;
    }
}
