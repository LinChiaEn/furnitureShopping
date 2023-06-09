package com.example.demo.controller;

import com.example.demo.entity.CartProduct;
import com.example.demo.entity.User;

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
@CrossOrigin("http://localhost:8081/")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{username}")
    public ResponseEntity<List<CartProduct>> getCart(@PathVariable("username") String username) {
        List<CartProduct> cart = cartService.getCart(username);
        return ResponseEntity.ok(cart);
    }
    
    @PostMapping("/{username}/add")
    public ResponseEntity<CartProduct> addProduct(@PathVariable("username") String username, @RequestBody CartProduct request) {
    	User user = cartService.getUser(username);
    	CartProduct product = cartService.findProduct(request.getName(),user);
    	CartProduct newProduct;
    	if(product != null)	newProduct = cartService.replaceNumOfProduct(user,product,true);
    	else newProduct = cartService.createProduct(user,request);
    	return ResponseEntity.ok(newProduct);
    	//return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartProduct> replaceProduct(@PathVariable("id") String id, @RequestBody CartProduct request) {
    	CartProduct product = cartService.replaceProduct(id, request);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{username}/reduce/{name}")
    public ResponseEntity reduceProduct(@PathVariable("username") String username,@PathVariable("name") String name) {
    	User user = cartService.getUser(username);
    	CartProduct product = cartService.findProduct(name,user);
    	if(product.getQuantity()>1)
    		cartService.replaceNumOfProduct(user,product,false);
    	else cartService.deleteProduct(user,name);
        return ResponseEntity.noContent().build();
    }
}
