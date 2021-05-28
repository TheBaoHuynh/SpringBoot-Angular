package com.baoht.ecommercebackend.controller;

import com.baoht.ecommercebackend.dto.RequestProductPage;
import com.baoht.ecommercebackend.entity.Product;
import com.baoht.ecommercebackend.repository.CustomProductRepository;
import com.baoht.ecommercebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CustomProductRepository customProductRepository;

    @PostMapping
    public ResponseEntity<Page<Product>> getProducts(@RequestBody RequestProductPage request) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts(request));
    }

    @PostMapping("/categories")
    public ResponseEntity<Page<Product>> getProductsByCategoryId(@RequestBody RequestProductPage request) {
        return ResponseEntity.status(HttpStatus.OK).body(customProductRepository.getProductByCategoryId(request));
    }


}
