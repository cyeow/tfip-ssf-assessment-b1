package ssf.pizza.b1ssfassessment.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Pizza {
    @NotNull(message="A type of pizza has to be selected.")
    private String pizza;
    
    @NotNull(message="A pizza size must be selected.")
    private String size;

    @NotNull
    @Min(value=1, message="Quantity has to be between 1 and 10 inclusive.")
    @Max(value=10, message="Quantity has to be between 1 and 10 inclusive.")
    private Integer quantity;
    
    public Pizza() {
        this.quantity = 1;
    }

    @Override
    public String toString() {
        return "Pizza [type=" + pizza + ", size=" + size + ", quantity=" + quantity + "]";
    }

    public Pizza(String pizzaType, String pizzaSize, Integer quantity) {
        this.pizza = pizzaType;
        this.size = pizzaSize;
        this.quantity = quantity;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizzaType) {
        this.pizza = pizzaType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String pizzaSize) {
        this.size = pizzaSize;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
