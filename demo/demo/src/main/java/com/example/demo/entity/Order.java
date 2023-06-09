package com.example.demo.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import com.example.demo.entity.CartProduct;

@Document(collection = "order")
public class Order {
	private List<CartProduct> order;
	private String receiver;
	private String address;
	
	public Order() {

    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public List<CartProduct> getOrder() {
        return order;
    }

    public void setOrder(List<CartProduct> order) {
        this.order = order;
    }
}
