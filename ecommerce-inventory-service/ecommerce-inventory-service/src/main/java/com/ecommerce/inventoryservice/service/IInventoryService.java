package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.request.InventoryRequest;
import com.ecommerce.inventoryservice.dto.request.IventoryFilterForm;
import com.ecommerce.inventoryservice.dto.request.LockProductRequest;
import com.ecommerce.inventoryservice.dto.response.InventoryResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface IInventoryService {
    List<InventoryResponse> filterInventory(IventoryFilterForm form);

    InventoryResponse createInventory(InventoryRequest inventoryRequest);

    InventoryResponse deleteInventory(String productId);

    Boolean lock(LockProductRequest lockProductRequest);
}
