package cn.pxy.order.service;


import cn.pxy.feign.clients.UserClient;
import cn.pxy.feign.pojo.User;
import cn.pxy.order.mapper.OrderMapper;
import cn.pxy.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        User user = userClient.findById(orderId);
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
}
