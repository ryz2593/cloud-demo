package cn.itcast.consumer.web;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ryz2593
 * @date 2020/1/14 17:23
 */
@RestController
@RequestMapping("consumer")
//给controller中的所有方法都添加降级处理
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private DiscoveryClient discoveryClient;

    //方式1
//    @Autowired
//    private RibbonLoadBalancerClient client;

    @GetMapping("{id}")
    //单个方法降级处理
    //@HystrixCommand(fallbackMethod = "queryByIdFallback")
    //设置超时时间，这个是仅仅为这个方法设置的，如果想要设置全局的超时时间，需要在yaml文件中配置，然后这里只加@HystrixCommand就行了
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @HystrixCommand(
            commandProperties = {
                    //设置触发熔断的最小请求次数，休眠时长，失败请求百分比
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            }
    )
    public String queryById(@PathVariable("id") Long id) {
        if (id % 2 == 0) {
            throw new RuntimeException("");
        }
        String url = "http://user-service/user/" + id;
        String user = restTemplate.getForObject(url, String.class);
        return user;
    }

    /**
     * 熔断方法的参数列表和返回值类型必须原方法保持一致
     *
     * @param id
     * @return
     */
    public String queryByIdFallback(Long id) {
        return "服务器拥挤";
    }

    /**
     * 通用降级方法
     *
     * @return
     */
    public String defaultFallback() {
        return "服务器拥挤";
    }

//    @GetMapping("{id}")
//    public User queryById(@PathVariable("id") Long id) {
//        //根据服务id，获取实例
//        //List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        //从实例中获取ip地址和端口
//        //ServiceInstance instance = instance.get(0);
//        //这里需要负载均衡算法 来获取一个实例
//
//        //方式1
////        ServiceInstance instance = client.choose("user-service");
////        String url = "http://"+instance.getHost() + ":" + instance.getPort()+"/user/" + id;
////        System.out.println("url = " + url);
//        //方式2
//        String url = "http://user-service/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }
}
