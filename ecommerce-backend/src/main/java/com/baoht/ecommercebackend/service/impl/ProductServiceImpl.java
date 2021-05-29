package com.baoht.ecommercebackend.service.impl;

import com.baoht.ecommercebackend.dto.response.ProductDTO;
import com.baoht.ecommercebackend.dto.request.RequestProductPage;
import com.baoht.ecommercebackend.dto.response.ResponsePageDTO;
import com.baoht.ecommercebackend.entity.Product;
import com.baoht.ecommercebackend.repository.CustomProductRepository;
import com.baoht.ecommercebackend.repository.ProductCriteriaRepository;
import com.baoht.ecommercebackend.repository.ProductRepository;
import com.baoht.ecommercebackend.service.ProductService;
import com.baoht.ecommercebackend.utils.MapUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCriteriaRepository productCriteriaRepository;
    private final CustomProductRepository customProductRepository;

    @Override
    public Page<Product> getProducts(RequestProductPage request) {
        return productCriteriaRepository.findAllWithFilter(request);
    }

    @Override
    public ResponsePageDTO getProductByCategoryId(RequestProductPage request) {
        Page<Product> products = customProductRepository.getProductByCategoryId(request);
        List<ProductDTO> productDTO = MapUtils.mapPage(products, ProductDTO.class);
        ResponsePageDTO<ProductDTO> responsePageDTO = new ResponsePageDTO();
        responsePageDTO.setResultList(productDTO);
        responsePageDTO.setTotalPage(products.getTotalPages());
        responsePageDTO.setPageNumber(request.getPageNumber());
        responsePageDTO.setItemPerPage(request.getItemPerPage());
        return responsePageDTO;
    }

}
