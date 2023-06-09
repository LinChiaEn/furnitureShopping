package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.CartProduct;
import com.example.demo.entity.Order;

import com.example.demo.service.OrderService;
import com.example.demo.service.CartService;
import org.springframework.http.MediaType;
import com.example.demo.parameter.ProductQueryParameter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import com.example.demo.parameter.ProductQueryParameter;

import jakarta.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shop/cart", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value={"http://localhost:8081/","http://localhost:8082/"})
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CartService cartService;
    
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order request) {
        Order order = orderService.createOrder(request);
        
        return ResponseEntity.ok(order);
    }
    
    @DeleteMapping("/{username}/remove")
    public ResponseEntity removeProduct(@PathVariable("username") String username) {
    	User user = cartService.getUser(username);
    	List<CartProduct> emptyCart = new ArrayList<CartProduct>();
    	cartService.setCart(emptyCart,user);
    	
        return ResponseEntity.noContent().build();
    }
}
