package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.client.dto.request.InventoryFilterForm;
import com.ecommerce.orderservice.client.dto.response.InventoryResponse;

import java.util.List;

public interface IIventoryClient {
    List<InventoryResponse> getIventory(InventoryFilterForm form);
}
