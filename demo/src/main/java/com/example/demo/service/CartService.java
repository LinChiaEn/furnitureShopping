package com.example.demo.service;

import com.example.demo.entity.CartProduct;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;

import com.example.demo.exception.NotFoundException;
import com.example.demo.parameter.ProductQueryParameter;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    public CartProduct getProduct(String name) {
        return repository.findByName(name);
    }
    
    public CartProduct findProduct(String name, User user) {
    	if(user.getCart()!=null) {
    		List<CartProduct> cart = user.getCart();
        	for(int i=0;i<cart.size();i++){
        		if(cart.get(i).getName().equals(name)) {
        			return cart.get(i);
        		}
        	}
    	}
    	return null;	
    }
    
    public CartProduct createProduct(User user, CartProduct request) {
    	CartProduct product = new CartProduct();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImg(request.getImg());
        product.setSale(request.getSale());
        product.setCategory(request.getCategory());
        product.setQuantity(1);
        user.getCart().add(product);
        userRepository.save(user);
        return product;
    }
    
    public CartProduct replaceNumOfProduct(User user, CartProduct product, boolean increse) {
    	if(increse) product.setQuantity(product.getQuantity()+1);
    	else product.setQuantity(product.getQuantity()-1);
    	for(int i=0;i<user.getCart().size();i++){
    		if(user.getCart().get(i).getName().equals(product.getName())) {
    			user.getCart().set(i,product);
    			break;
    		}
    	}
    	userRepository.save(user);
    	return product;
    }
    
    public CartProduct replaceProduct(String id, CartProduct request) {
    	CartProduct oldProduct = getProduct(id);

    	CartProduct product = new CartProduct();
        product.setId(oldProduct.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return repository.save(product);
    }

    public void deleteProduct(User user,String name) {
    	for(int i=0;i<user.getCart().size();i++){
    		if(user.getCart().get(i).getName().equals(name)) {
    			user.getCart().remove(user.getCart().get(i));
    			userRepository.save(user);
    			break;
    		}
    	}
    }
    
    public List<CartProduct> getCart(String username) {
    	User user=getUser(username);
        
        return user.getCart();
    }
    
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User setCart(List<CartProduct> cart,User user) {
    	user.setCart(cart);
        return userRepository.save(user);
    }
}
