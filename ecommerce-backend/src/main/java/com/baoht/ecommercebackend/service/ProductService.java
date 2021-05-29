package com.baoht.ecommercebackend.service;

import com.baoht.ecommercebackend.dto.request.RequestProductPage;
import com.baoht.ecommercebackend.dto.response.ResponsePageDTO;
import com.baoht.ecommercebackend.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<Product> getProducts(RequestProductPage request);

    ResponsePageDTO getProductByCategoryId(RequestProductPage request);
}
