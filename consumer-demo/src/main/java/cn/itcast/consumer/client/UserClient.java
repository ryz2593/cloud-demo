package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ryz2593
 * @date 2020/1/15 17:04
 */
//使用fallback设置熔断类----UserClientFallback
@FeignClient(value = "user-service", fallback = UserClientFallback.class)
public interface UserClient {

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    User queryById(@PathVariable("id") Long id);
}
