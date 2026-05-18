package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.common.BaseResponse;
import com.ecommerce.inventoryservice.dto.request.InventoryRequest;
import com.ecommerce.inventoryservice.dto.request.IventoryFilterForm;
import com.ecommerce.inventoryservice.dto.request.LockProductRequest;
import com.ecommerce.inventoryservice.dto.response.InventoryResponse;
import com.ecommerce.inventoryservice.service.IInventoryService;
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
@RequestMapping("api/v1/inventories")
@Validated
public class IventoryController {

    private final IInventoryService inventoryService;

    @PostMapping
    public ResponseEntity<BaseResponse<List<InventoryResponse>>> filterInventory(@RequestBody IventoryFilterForm form){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse<>(inventoryService.filterInventory(form), "Success"));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<InventoryResponse>> createInventory(@RequestBody InventoryRequest inventoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(inventoryService.createInventory(inventoryRequest), "Success"));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<Boolean>> lock(@RequestBody @Valid LockProductRequest lockProductRequest) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body( new BaseResponse<>(inventoryService.lock(lockProductRequest),"Success"));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<BaseResponse<InventoryResponse>> deleteInventory(@PathVariable String productId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse<>(inventoryService.deleteInventory(productId), "Success"));
    }


}
