一般引入一个springcloud中使用的组件步骤：

1. 引入依赖

2. 在启动类上添加注解：如@EnableEurekaServer,@EnableDiscoveryClient等

3. 修改对外端口，在application.yaml中设置server.port