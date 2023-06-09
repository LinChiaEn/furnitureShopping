package com.example.demo.repository;

import com.example.demo.entity.Order;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByReceiverLikeIgnoreCase(String name);

}
