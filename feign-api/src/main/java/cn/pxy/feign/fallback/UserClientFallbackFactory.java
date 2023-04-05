package cn.pxy.feign.fallback;

import cn.pxy.feign.clients.UserClient;
import cn.pxy.feign.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;



/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-04-05 13:36
 **/

@Slf4j
//@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public User findById(Long id) {
                log.error("findById error", throwable);
                return new User();
            }
        };
    }
}
