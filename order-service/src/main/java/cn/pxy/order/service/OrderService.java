package cn.pxy.order.service;


import cn.pxy.feign.clients.UserClient;
import cn.pxy.feign.pojo.User;
import cn.pxy.order.mapper.OrderMapper;
import cn.pxy.order.pojo.Order;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
//    @Lazy //循环依赖 延迟注入，懒加载
    private UserClient userClient;
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        User user = userClient.findById(order.getUserId());
        log.info("user:{}" ,user);
        order.setUser(user);
        // 4.返回
        return order;
    }

  /*  @Autowired
    private RestTemplate restTemplate;
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.利用RestTemplate发送http请求，查询用户
        String url = "http://userservice/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
        // 4.返回
        return order;
    }*/
    //sentinel 监控 service
    @SentinelResource("goods")
    public void queryGoods(){
        System.out.println("查询商品");
    }
}
