package com.ecommerce.inventoryservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IventoryFilterForm {

    List<String> ids;
    private Integer minQuantiy;
    private Integer maxQuantity;
}
