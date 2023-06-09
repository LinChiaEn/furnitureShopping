package com.example.demo.entity;

import com.example.demo.entity.CartProduct;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	private String id;
    private String username;
    private String password;
    private List<CartProduct> cart;
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<CartProduct> getCart() {
        return cart;
    }
    
    public void setCart(List<CartProduct> cart) {
        this.cart = cart;
    }
}
