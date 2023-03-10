package ssf.pizza.b1ssfassessment.service;

import org.springframework.stereotype.Service;

import ssf.pizza.b1ssfassessment.model.Order;
import ssf.pizza.b1ssfassessment.model.Pizza;

@Service
public class PizzaService {
    
    public boolean isValidPizzaType(Pizza pizza) {

        if(pizza.getPizza() == null) {
            return false;
        }

        switch(pizza.getPizza()) {
            case "bella":
            case "margherita":
            case "marinara":
            case "spianatacalabrese":
            case "trioformaggio":
                break;
            default:
                return false;
        }

        return true;
    }

    public boolean isValidPizzaSize(Pizza pizza) {

        if(pizza.getSize() == null) {
            return false;
        }

        switch(pizza.getSize()) {
            case "sm":
            case "md":
            case "lg":
                break;
            default:
                return false;
        }

        return true;
    }

    public Double getPizzaCost(Pizza pizza) {
        Double multiplier = 0d;
        switch(pizza.getSize()) {
            case "sm":
                multiplier = 1d;
            case "md":
                multiplier = 1.2d;
            case "lg":
                multiplier = 1.5d;
        }

        switch(pizza.getPizza()) {
            case "bella":
            case "marinara":
            case "spianatacalabrese":
                return 30d * multiplier * pizza.getQuantity();
            case "margherita":
                return 22d * multiplier * pizza.getQuantity();
            case "trioformaggio":
                return 25d * multiplier * pizza.getQuantity();
            default:
                return 0d * multiplier * pizza.getQuantity();
        }
    }

    public Double getTotalCost(Order order) {
        if(order.isRush()) {
            return getPizzaCost(order.getPizza()) + 2d;
        } else {
            return getPizzaCost(order.getPizza());
        }
    }
}
