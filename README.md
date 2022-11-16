# 🍁gRPC-Java-Study
## 🎯技术栈
| Name       | Version | Links                                                        |
|------------|---------|--------------------------------------------------------------|
| SpringBoot | 2.4.0   |                                                              |
| gRPC       | 1.50.2  | https://grpc.io/                                             |
| protobuf   | 1.4     | https://developers.google.com/protocol-buffers/docs/overview |
| gradle     | 7.4     | https://docs.gradle.org/current/userguide/userguide.html     |


## 🧋项目结构
在Terminal输入 gradle -q projects，输出如下：
```markdown
Root project 'gRPC-Java-Study'
+--- Project ':user-api' - user-api
+--- Project ':user-client' - user-client
\--- Project ':user-server' - user-server
```

### 🍉user-api
接口模块，用于保存使用proto文件生成的java接口类。

### 🍇user-client
客户端模块，proto接口的客户端。

### 🍒user-service
服务端模块，实现proto接口的服务。

## 🐳项目构建
暂无

## 📖规范
暂无

## 整合nacos
参考：https://github.dev/nacos-group/grpc-java-registry-nacos

前置条件

| 必须项          | 说明      |
|--------------|---------|
| Nacos Server | nacos服务 |



引入依赖
```groovy
dependencies {
    implementation "com.alibaba.nacos:nacos-client"
}
```
步骤：
+ 重写NameResolver
+ 重写NameResolverProvider