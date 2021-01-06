package com.nyd.dsdeliver.services;

import com.nyd.dsdeliver.dto.OrderDTO;
import com.nyd.dsdeliver.dto.ProductDTO;
import com.nyd.dsdeliver.entities.Order;
import com.nyd.dsdeliver.entities.OrderStatus;
import com.nyd.dsdeliver.entities.Product;
import com.nyd.dsdeliver.repositories.OrderRepository;
import com.nyd.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> getAll(){
        List<Order> orders = repository.findOrderWithProducts();
        return orders.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }


    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
        for(ProductDTO p: dto.getProducts()){
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }

        order = repository.save(order);
        return new OrderDTO(order);
    }

}
