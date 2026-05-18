package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.common.BaseResponse;
import com.ecommerce.orderservice.dto.request.OrderItemFilterForm;
import com.ecommerce.orderservice.dto.response.OrderItemResponse;
import com.ecommerce.orderservice.service.IOrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/order_items")
public class OrderItemController {

    private final IOrderItemService orderItemService;

    @GetMapping("/filter")
    public ResponseEntity<BaseResponse<List<OrderItemResponse>>> filterOrderItem(OrderItemFilterForm form){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse<>(orderItemService.filterOrderItem(form), "Success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderItemResponse>> deletedById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(orderItemService.deletedById(id), "Deleted"));
    }

}
