package com.baoht.ecommercebackend.repository;

import com.baoht.ecommercebackend.dto.request.RequestProductPage;
import com.baoht.ecommercebackend.entity.Product;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ProductCriteriaRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Product> findAllWithFilter(RequestProductPage request) {

        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(request, productRoot);
        criteriaQuery.where(predicate);
        setOrder(request, criteriaQuery, productRoot);
        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((request.getPageNumber() - 1) * request.getItemPerPage());
        typedQuery.setMaxResults(request.getItemPerPage());

        Pageable pageable = getPageable(request);

        long productCount = getProductCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, productCount);
    }

    private long getProductCount(Predicate predicate){
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(RequestProductPage request) {
        Sort sort = Sort.by(request.getSortBy(), request.getSortByColumn());
        return PageRequest.of(request.getPageNumber() - 1, request.getItemPerPage(), sort);
    }

    private Predicate getPredicate(RequestProductPage request,
                                   Root<Product> productRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(request.getKeyword())) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(productRoot.get("name"),
                                    "%" + request.getKeyword() + "%"),
                            criteriaBuilder.like(productRoot.get("sku"),
                                    "%" + request.getKeyword() + "%")
                    )
            );
        }
        if (Objects.nonNull(request.getStatus())) {
            predicates.add(
                    criteriaBuilder.like(productRoot.get("status"),
                            request.getStatus())
            );
        }
//        if (Objects.nonNull(request.getCategoryId())) {
//            predicates.add(
//                    criteriaBuilder.like(productRoot.get("category").get("id"),
//                            request.getCategoryId())
//            );
//        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(RequestProductPage request, CriteriaQuery<Product> criteriaQuery, Root<Product> productRoot) {

        if (request.getSortByColumn().equals("id") && request.getSortBy().equals("asc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get("id")));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get("id")));
        }
    }
}
