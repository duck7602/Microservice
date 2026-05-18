package com.ecommerce.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFIlterForm {

    private String search;
    private String id;
    private List<String> ids;
    private String name;
    private Integer price;
    private String categoryId;
}
