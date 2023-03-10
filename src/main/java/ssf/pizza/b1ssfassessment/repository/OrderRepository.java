package ssf.pizza.b1ssfassessment.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ssf.pizza.b1ssfassessment.model.Order;

@Repository
public class OrderRepository {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private static final String ORDERS = "orders";
    private static final String ORDERS_MAP = ORDERS + "_Map";

    public OrderRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Order findById(String orderId) {
        return (Order) redisTemplate.opsForHash().get(ORDERS_MAP, orderId);
    }

    public Order saveOrder(Order order) {
        redisTemplate.opsForList().leftPush(ORDERS, order.getOrderId());
        redisTemplate.opsForHash().put(ORDERS_MAP, order.getOrderId(), order.toString()); // change to json

        return findById(order.getOrderId());
    }

    public List<Order> findAllOrders() {
        List<Object> orderList = redisTemplate.opsForList().range(ORDERS,0,100).stream().map(Object.class::cast).toList();

        List<Order> orders = redisTemplate.opsForHash().multiGet(ORDERS_MAP, orderList).stream().map(Order.class::cast).filter(Order.class::isInstance).toList();

        return orders;
    }
    // add
    // read all
    // read one
    // update
    // delete
}
