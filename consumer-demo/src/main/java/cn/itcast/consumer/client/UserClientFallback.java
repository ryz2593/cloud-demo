package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.stereotype.Component;

/**
 * 熔断的异常逻辑
 * @author ryz2593
 * @date 2020/1/17 15:51
 */
@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setName("未知用户!");
        return user;
    }
}
