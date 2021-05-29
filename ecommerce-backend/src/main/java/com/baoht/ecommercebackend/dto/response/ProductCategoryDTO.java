package com.baoht.ecommercebackend.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductCategoryDTO {

    private Long id;

    private String name;

    @JsonManagedReference
    private Set<ProductDTO> products;

}
