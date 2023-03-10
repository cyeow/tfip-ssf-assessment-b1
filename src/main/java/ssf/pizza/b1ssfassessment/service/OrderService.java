package ssf.pizza.b1ssfassessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssf.pizza.b1ssfassessment.model.Order;
import ssf.pizza.b1ssfassessment.model.Pizza;
import ssf.pizza.b1ssfassessment.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepo;
    
    public void confirmOrder(Order order, Pizza pizza) {
        order.setOrderId();
        order.setPizza(pizza);
        saveOrder(order);
    }

    public Order findOrder(String orderId) {
        return orderRepo.findById(orderId);
    }

    public void saveOrder(Order order) {
        orderRepo.saveOrder(order);
    }
    
}
