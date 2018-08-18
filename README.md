#系统架构
***
- 目前以springboot2.0.0.RELEASE为框架基础,搭建springcloud2.0微服务框架体系
采用spring-cloud-consul2.0作为服务发现和配置中心,至于不使用zookeeper作为配置
中心主要是因为springcloud-zookeeper是cp类型,不保证可用性,且其他功能如健康检查
都没有consul强大,另外consul是ca类型,然而不选择Eureka是因为它2.0闭源了,所以该系
统采用consul.另外系统集成springboot-data中其他模块,方便其他模块集成
***

## robot-access 机器人接入模块
- 该模块下面全部使用springboot2.0.4,并且集成spring-cloud-consul2.0作为配置中心
实现健康检查，该模块下面的必须是启动程序

###&nbsp;&nbsp;&nbsp;&nbsp;websocket-access websocket接入方式
- websocket方式接入平台，目前使用的工具类:

**lombok1.16.6**(如果其他模块都需要,可转移到父模块)
**netty-socketio1.7.12**(websocket实现需要)
**rom-redis0.0.1-SNAPSHOT**（redis公共模块）
**spring-boot-starter-web2.0.4.RELEASE**（健康检查需要）



