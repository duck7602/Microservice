package com.ecommerce.orderservice.events;

import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderCreatedEvent extends Order {
        List<OrderItem> orderItemList;
}
