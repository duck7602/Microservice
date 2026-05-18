package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.common.BaseResponse;
import com.ecommerce.orderservice.dto.request.OrderFilterForm;
import com.ecommerce.orderservice.dto.request.OrderRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.service.IOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/orders")
@Validated
public class OrderController {

    private final IOrderService orderService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<OrderResponse>>> getAllOrder(){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(orderService.getAllOrder(), "Success"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderResponse>> getOrderById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(orderService.getOrderById(id), "Success"));
    }

    @GetMapping("/filter")
    public ResponseEntity<BaseResponse<List<OrderResponse>>> filterOrder(OrderFilterForm form){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(orderService.filterOrder(form), "Success"));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<OrderResponse>> createOrder(@Valid @RequestBody OrderRequest orderRequest) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse<>(orderService.createOrder(orderRequest), "Success"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderResponse>> deleteOrderById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(orderService.deleteOrderById(id),"Deleted"));
    }
}
