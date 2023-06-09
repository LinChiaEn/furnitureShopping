package controller;

import entity.CartProduct;

import service.CartService;
import org.springframework.http.MediaType;
import parameter.ProductQueryParameter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import parameter.ProductQueryParameter;

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
    private CartService productService;

    @GetMapping("/{id}")
    public ResponseEntity<CartProduct> getProduct(@PathVariable("id") String id) {
    	CartProduct product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<CartProduct>> getProducts(@ModelAttribute ProductQueryParameter param) {
        List<CartProduct> products = productService.getProducts(param);
        return ResponseEntity.ok(products);
    }
    
    @PostMapping("/add")
    public ResponseEntity<CartProduct> addProduct(@RequestBody CartProduct request) {
    	CartProduct product = productService.findProduct(request);
    	CartProduct newProduct;
    	if(product != null)	newProduct = productService.replaceNumOfProduct(product.getId(),true);
    	else newProduct = productService.createProduct(request);
    	return ResponseEntity.ok(product);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<CartProduct> replaceProduct(
            @PathVariable("id") String id, @RequestBody CartProduct request) {
    	CartProduct product = productService.replaceProduct(id, request);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/reduce/{id}")
    public ResponseEntity reduceProduct(@PathVariable("id") String id) {
    	CartProduct product = productService.getProduct(id);
    	if(product.getQuantity()>1)
    		productService.replaceNumOfProduct(product.getId(),false);
    	else productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
