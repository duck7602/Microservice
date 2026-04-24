package com.order_service.mapper;

import com.order_service.dto.response.OrderItemResponse;
import com.order_service.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T14:07:21+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public List<OrderItemResponse> toOrderItem(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemResponse> list = new ArrayList<OrderItemResponse>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( orderItemToOrderItemResponse( orderItem ) );
        }

        return list;
    }

    protected OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponse orderItemResponse = new OrderItemResponse();

        orderItemResponse.setId( orderItem.getId() );
        orderItemResponse.setOrderId( orderItem.getOrderId() );
        orderItemResponse.setProductId( orderItem.getProductId() );
        orderItemResponse.setPrice( orderItem.getPrice() );
        orderItemResponse.setQuantity( orderItem.getQuantity() );

        return orderItemResponse;
    }
}
