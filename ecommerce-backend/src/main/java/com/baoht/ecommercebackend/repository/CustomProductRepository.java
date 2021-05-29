package com.baoht.ecommercebackend.repository;

import com.baoht.ecommercebackend.dto.request.RequestProductPage;
import com.baoht.ecommercebackend.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomProductRepository {

    private final ProductRepository productRepository;

    public Page<Product> getProductByCategoryId(RequestProductPage request){

        Pageable pageable = getPageable(request);

        Page<Product> products = productRepository.findAll(
                Specification.where(nameLike(request.getKeyword()))
                .and(statusEqual(request.getStatus())), pageable
        );

        return products;
    }

    private Pageable getPageable(RequestProductPage request) {
        List<Sort.Order> orderList = new ArrayList<>();
        Sort sort = Sort.by(request.getSortByColumn());
        return PageRequest.of(request.getPageNumber() - 1, request.getItemPerPage(), sort);
    }

    private Specification<Product> nameLike(String name){
        return null != name ? (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%"+name+"%") : null;
    }

    private Specification<Product> statusEqual(String status){
        return null != status ? (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get("status"), status) : null;
    }




}
