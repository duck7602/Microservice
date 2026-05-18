package com.ecommerce.inventoryservice.mapper;

import com.ecommerce.inventoryservice.dto.request.InventoryRequest;
import com.ecommerce.inventoryservice.dto.response.InventoryResponse;
import com.ecommerce.inventoryservice.entity.Inventory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-06T19:01:04+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public Inventory toEntity(InventoryRequest inventoryRequest) {
        if ( inventoryRequest == null ) {
            return null;
        }

        Inventory inventory = new Inventory();

        inventory.setProductId( inventoryRequest.getProductId() );
        inventory.setQuantity( inventoryRequest.getQuantity() );

        return inventory;
    }

    @Override
    public InventoryResponse toResponse(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        InventoryResponse inventoryResponse = new InventoryResponse();

        inventoryResponse.setProductId( inventory.getProductId() );
        inventoryResponse.setQuantity( inventory.getQuantity() );
        inventoryResponse.setIsDeleted( inventory.getIsDeleted() );
        inventoryResponse.setCreatedBy( inventory.getCreatedBy() );
        inventoryResponse.setCreatedDate( inventory.getCreatedDate() );
        inventoryResponse.setLastModifiedBy( inventory.getLastModifiedBy() );
        inventoryResponse.setLastModifiedDate( inventory.getLastModifiedDate() );

        return inventoryResponse;
    }

    @Override
    public List<Inventory> toEntityList(List<InventoryRequest> inventoryRequests) {
        if ( inventoryRequests == null ) {
            return null;
        }

        List<Inventory> list = new ArrayList<Inventory>( inventoryRequests.size() );
        for ( InventoryRequest inventoryRequest : inventoryRequests ) {
            list.add( toEntity( inventoryRequest ) );
        }

        return list;
    }

    @Override
    public List<InventoryResponse> toResponseList(List<Inventory> inventories) {
        if ( inventories == null ) {
            return null;
        }

        List<InventoryResponse> list = new ArrayList<InventoryResponse>( inventories.size() );
        for ( Inventory inventory : inventories ) {
            list.add( toResponse( inventory ) );
        }

        return list;
    }
}
