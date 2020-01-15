package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ryz2593
 * @date 2020/1/14 17:18
 */
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
//这个注解包含上面三个

@EnableFeignClients
@SpringCloudApplication
public class ConsumerApplication {
//    @Bean
//    //方式2
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
