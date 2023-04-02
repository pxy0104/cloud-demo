package cn.pxy.order.clients;


import cn.pxy.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userservice") //服务名称
public interface UserClient {
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id")Long id);
}
