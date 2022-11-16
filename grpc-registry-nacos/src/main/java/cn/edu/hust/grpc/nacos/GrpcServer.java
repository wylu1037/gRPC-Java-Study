package cn.edu.hust.grpc.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerServiceDefinition;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.util.MutableHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Grpc Server, 注册服务到nacos中
 */
public class GrpcServer {
	private final Logger logger = LoggerFactory.getLogger(GrpcServer.class);

	protected int port;

	protected volatile boolean started;

	public Server server;

	/**
	 * service registry
	 */
	protected MutableHandlerRegistry handlerRegistry = new MutableHandlerRegistry();

	/**
	 * the mapping relationship between BindableService and ServerServiceDefinition
	 */
	protected ConcurrentHashMap<BindableService, ServerServiceDefinition> serviceInfo =
		new ConcurrentHashMap<>();

	protected AtomicInteger invokerCnt = new AtomicInteger();

	private NamingService namingService = null;

	/**
	 *
	 * @param port grpc server的端口
	 * @param properties 注册中心nacos的信息
	 * @param services grpc service
	 */
	public void init(int port, Properties properties,BindableService[] services) {
		this.port = port;
		this.server = NettyServerBuilder.forPort(port)
			.fallbackHandlerRegistry(handlerRegistry)
			.build();
		try {
			this.namingService = NacosFactory.createNamingService(properties);
		} catch (NacosException e) {
			logger.error("init grpc server failed, error: {}", e.getErrMsg());
		}
		for (BindableService service : services) {
			registryService(service);
		}
	}

	public void registryService(Object ref) {
		BindableService service = (BindableService) ref;
		ServerServiceDefinition serverServiceDefinition = service.bindService();
		serviceInfo.put(service, serverServiceDefinition);

		try {
			namingService.registerInstance(serverServiceDefinition.getServiceDescriptor().getName(), createInstance());
			handlerRegistry.addService(serverServiceDefinition);
			invokerCnt.incrementAndGet();
		} catch (NacosException e) {
			logger.error("Register grpc service error ", e);
		}
	}

	private Instance createInstance() {
		Instance instance = new Instance();
		instance.setIp("127.0.0.1");
		instance.setPort(port);
		return instance;
	}

	public void start() {
		if (started) {
		    return;
		}
		synchronized (this) {
			try {
				server.start();
				logger.info("grpc server started at port {}", port);
			} catch (Exception e) {
				logger.error("grpc server started error, msg: {}", e.getMessage());
			}
		}
	}

	public void stop() {
		if (!started) {
			return;
		}
		try {
			// 关闭端口，不关闭线程池
			logger.info("Stop the http rest server at port {}", port);
			server.shutdown();
		} catch (Exception e) {
			logger.error("Stop the http rest server at port " + port + " error !", e);
		}
		started = false;
	}
}
