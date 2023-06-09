package com.example.demo.controller;

import com.example.demo.entity.User;

import com.example.demo.service.LoginService;
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
@RequestMapping(value = "/shop/login", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("http://localhost:8081/")
public class LoginController {
	
	@Autowired
    private LoginService loginService;
	
	@PostMapping
    public ResponseEntity login(@RequestBody User request) {
    	User user = loginService.getUser(request);
    	if(user != null) {
    		if (request.getPassword().equals(user.getPassword())) {
        		return ResponseEntity.accepted().build();
        	}
        	else return ResponseEntity.ok().build();
    	}
    	else return ResponseEntity.ok().build();
    	
		//return ResponseEntity.ok(user);
    }
}