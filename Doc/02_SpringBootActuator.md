## POM依赖
~~~
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
~~~

## 监控端点{endpoint}
| 端点(SpringBoot2.X) | 描述 | HTTP方法 | 是否敏感 | 端点(SpringBoot1.X) |
| ------ | ------ | ------ | ------ | ------ |
| conditions | 自动配置的信息 | GET | 是 | autoconfig  |
| beans | 应用程序上下文所有的Spring bean | GET | 是 | beans  |
| configprops | 所有@ConfigurationProperties配置属性列表 | GET | 是 | configprops  |
| dump | 线程活动的快照 | GET | 是 | dump  |
| env | 环境变量，包括系统环境变量以及应用环境变量 | GET | 是 | env  |
| health | 显示应用程序的健康指标，值由HealthIndicator的实现类提供；结果有UP、 DOWN、OUTOFSERVICE、UNKNOWN，如需查看看详情，需配置：management.endpoint.health.show-details | GET | 否 | health  |
| info | 应用的信息，可使用 info.*属性自定义info端点公开的数据 | GET | 否 | info  |
| mappings | 所有的URL路径 | GET | 是 | mappings  |
| metrics | 应用的度量标准信息 | GET | 是 | metrics  |

## 访问方式
- 2.0.x 以后SpringBoot的actuator启动端点监控web端默认加载默认只有两个info, health可见的页面节点
- 默认情况下，敏感路径并不暴露。如需暴露（以metrics为例），需添加配置

~~~
management:
  endpoints:
    web:
      exposure:
        include: "*"
~~~


http://{IP}:{PORT}/actuator/{endpoint}

### /conditions
http://localhost:8080/actuator/conditions
![avatar](https://github.com/rothschil/static/raw/master/images/springboot/1-conditions.jpg)

### /configprops
http://localhost:8080/actuator/configprops
![avatar](https://github.com/rothschil/static/raw/master/images/springboot/2-configprops.jpg)

### /metrics
http://localhost:8080/actuator/metrics
![avatar](https://github.com/rothschil/static/raw/master/images/springboot/metrics.jpg)
http://{IP}:{PORT}/actuator/metrics/{name}，{name}列表如上，即可查看当前应用的度量指标

例如：http://localhost:8080/actuator/metrics/jvm.memory.max
![avatar](https://github.com/rothschil/static/raw/master/images/springboot/metrics-jvm.memory.max.jpg)

### /mappings
http://localhost:8080/actuator/mappings
![avatar](https://github.com/rothschil/static/raw/master/images/springboot/3-mapping.jpg)

### /health
http://localhost:8080/actuator/health
![avatar](https://github.com/rothschil/static/raw/master/images/springboot/5-health.jpg)

### /beans
http://localhost:8080/actuator/beans
![avatar](https://github.com/rothschil/static/raw/master/images/springboot/6-beans.jpg)

### /env
http://localhost:8080/actuator/env
![avatar](https://github.com/rothschil/static/raw/master/images/springboot/7-env.jpg)