package ssf.pizza.b1ssfassessment.model;

import java.security.SecureRandom;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Order {
    private String orderId;

    @NotNull(message="Name is required.")
    @Size(min=3, message="Minimum 3 characters.")
    private String name;

    @NotNull(message="Address is required.")
    private String address;

    @NotNull(message="Phone number is required.")
    @Size(min=8, max=8, message="Phone number must be 8 digits.")
    private String phone;
    private boolean rush;
    private String comments;
    
    private Pizza pizza;
    
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId() {
        SecureRandom sr = new SecureRandom();
        StringBuilder temp = new StringBuilder();
        
        while(temp.length() < 8) {
            temp.append(Integer.toHexString(sr.nextInt(16)));
        }

        this.orderId = temp.toString();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
}
