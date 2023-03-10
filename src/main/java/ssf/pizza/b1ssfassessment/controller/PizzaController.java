package ssf.pizza.b1ssfassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import ssf.pizza.b1ssfassessment.model.Order;
import ssf.pizza.b1ssfassessment.model.Pizza;
import ssf.pizza.b1ssfassessment.service.OrderService;
import ssf.pizza.b1ssfassessment.service.PizzaService;

@Controller
@RequestMapping("/")
@SessionAttributes("pizza")
public class PizzaController {
    
    @Autowired
    PizzaService psvc;

    @Autowired
    OrderService osvc;

    public PizzaController(PizzaService psvc) {
        this.psvc = psvc;
    }

    @GetMapping
    public String indexPage(Model model) {
        return "view0";
    }

    @PostMapping("/pizza")
    public String submitOrder(Model model, @Valid @ModelAttribute("pizza") Pizza pizza, BindingResult binding) {
        if(!psvc.isValidPizzaType(pizza)) {
            binding.addError(new FieldError("pizza","type","Invalid pizza type selected."));
        }
        if(!psvc.isValidPizzaSize(pizza)) {
            binding.addError(new FieldError("pizza","size","Invalid pizza size selected."));
        }
        if(binding.hasErrors()) {
            if(binding.getFieldError("quantity") != null) {
                if(binding.getFieldError("quantity").getCode().equalsIgnoreCase("max")) {
                    pizza.setQuantity(10);
                } else if (binding.getFieldError("quantity").getCode().equalsIgnoreCase("min")) {
                    pizza.setQuantity(1);
                }    
            }
            return "view0";
        }
        Order order = new Order();
        model.addAttribute("order", order);
        return "view1";
    }

    @PostMapping("/pizza/order")
    public String submitDeliveryDetails(Model model, @Valid Order order, BindingResult binding) {
        if(binding.hasErrors()) {
            return "view1";
        }
        
        Pizza pizza = (Pizza)model.getAttribute("pizza");
        osvc.confirmOrder(order, pizza);

        model.addAttribute("order", order);
        model.addAttribute("pizzaCost", psvc.getPizzaCost(pizza));
        model.addAttribute("totalCost", psvc.getTotalCost(order));
        return "view2";
    }

    @GetMapping("/order/{orderId}")
    public String viewOrder(Model model, @PathVariable String orderId, BindingResult binding) {
        
        Order order = osvc.findOrder(orderId);

        model.addAttribute("order", order);
        model.addAttribute("pizzaCost", psvc.getPizzaCost(order.getPizza()));
        model.addAttribute("totalCost", psvc.getTotalCost(order));
        return "view2";
    }

    @ModelAttribute("pizza")
    public Pizza pizza() {
        return new Pizza();
    }
}
