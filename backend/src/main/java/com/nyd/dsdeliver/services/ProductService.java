package com.nyd.dsdeliver.services;

import com.nyd.dsdeliver.dto.ProductDTO;
import com.nyd.dsdeliver.entities.Product;
import com.nyd.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAll(){
        List<Product> products = repository.findAllByOrderByNameAsc();
        return products.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }



}
