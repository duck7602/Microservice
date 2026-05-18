package com.ecommerce.inventoryservice.mapper;

import com.ecommerce.inventoryservice.dto.request.InventoryRequest;
import com.ecommerce.inventoryservice.dto.response.InventoryResponse;
import com.ecommerce.inventoryservice.entity.Inventory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    Inventory toEntity(InventoryRequest inventoryRequest);
    InventoryResponse toResponse(Inventory inventory);
    List<Inventory> toEntityList(List<InventoryRequest> inventoryRequests);
    List<InventoryResponse> toResponseList(List<Inventory> inventories);

}
