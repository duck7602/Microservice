package com.vti.product_service.mapper;

import com.vti.product_service.dto.request.LockProductItemRequest;
import com.vti.product_service.dto.request.LockProductRequest;
import com.vti.product_service.event.ProductLockedEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T14:10:36+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class ProductLockedMapperImpl implements ProductLockedMapper {

    @Override
    public ProductLockedEvent toEvent(LockProductRequest lockProductRequest) {
        if ( lockProductRequest == null ) {
            return null;
        }

        ProductLockedEvent productLockedEvent = new ProductLockedEvent();

        List<LockProductItemRequest> list = lockProductRequest.getLockProductItemRequests();
        if ( list != null ) {
            productLockedEvent.setLockProductItemRequests( new ArrayList<LockProductItemRequest>( list ) );
        }
        productLockedEvent.setOrderId( lockProductRequest.getOrderId() );

        return productLockedEvent;
    }
}
