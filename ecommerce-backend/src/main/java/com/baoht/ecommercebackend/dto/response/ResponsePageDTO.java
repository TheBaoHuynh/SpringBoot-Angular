package com.baoht.ecommercebackend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePageDTO<T> {

    private int pageNumber;
    private int itemPerPage;
    private int totalPage;
    private List<T> resultList;
}
