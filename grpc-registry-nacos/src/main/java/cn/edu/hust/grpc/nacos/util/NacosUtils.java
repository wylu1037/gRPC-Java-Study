package cn.edu.hust.grpc.nacos.util;

import com.alibaba.nacos.api.PropertyKeyConst;

import java.net.URI;
import java.util.Properties;

/**
 * @Author wylu
 * @Date 2022/11/15 上午 08:52
 */
public class NacosUtils {

    public static Properties buildNacosProperties(URI uri, Properties properties) {
        String serverAddr = uri.getHost() + ":" + uri.getPort();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        return properties;
    }
}
