package com.baoht.ecommercebackend.dto.response;


import com.baoht.ecommercebackend.dto.response.ProductCategoryDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProductDTO {

    private Long id;

    @JsonBackReference
    private ProductCategoryDTO category;

    private String sku;

    private String name;

    private String description;

    private BigDecimal unitPrice;

    private String imageUrl;

    private boolean active;

    private int unitsInStock;

    private String status;

    private Date dateCreated;

    private Date lastUpdated;

}
