package com.example.demo.service;

import com.example.demo.entity.User;

import com.example.demo.exception.NotFoundException;
import com.example.demo.parameter.ProductQueryParameter;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoginService {
	
	@Autowired
    private UserRepository repository;
	
	public User getUser(User request) {
        return repository.findByUsername(request.getUsername());
    }
}
