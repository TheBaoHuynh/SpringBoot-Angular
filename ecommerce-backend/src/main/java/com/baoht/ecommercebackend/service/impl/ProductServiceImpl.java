package com.baoht.ecommercebackend.service.impl;

import com.baoht.ecommercebackend.dto.RequestProductPage;
import com.baoht.ecommercebackend.entity.Product;
import com.baoht.ecommercebackend.repository.ProductCriteriaRepository;
import com.baoht.ecommercebackend.repository.ProductRepository;
import com.baoht.ecommercebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCriteriaRepository productCriteriaRepository;

    @Override
    public Page<Product> getProducts(RequestProductPage request) {
        return productCriteriaRepository.findAllWithFilter(request);
    }
}
