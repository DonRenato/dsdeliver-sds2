package com.nyd.dsdeliver.services;

import com.nyd.dsdeliver.dto.OrderDTO;
import com.nyd.dsdeliver.entities.Order;
import com.nyd.dsdeliver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public List<OrderDTO> getAll(){
        List<Order> orders = repository.findOrderWithProducts();
        return orders.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }



}
