package com.example.demo.service;

import com.example.demo.entity.CartProduct;
import com.example.demo.entity.Order;

import com.example.demo.exception.NotFoundException;
import com.example.demo.parameter.ProductQueryParameter;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    
    public Order createOrder(Order request) {
    	Order order = new Order();
    	order.setReceiver(request.getReceiver());
    	order.setAddress(request.getAddress());
        order.setOrder(request.getOrder());

        return repository.insert(order);
    }
    
    public List<Order> getOrder() {
        
        return repository.findByReceiverLikeIgnoreCase("");
    }
}
