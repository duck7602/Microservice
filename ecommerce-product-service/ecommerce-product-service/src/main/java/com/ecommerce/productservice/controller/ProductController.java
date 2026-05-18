package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.common.BaseResponse;
import com.ecommerce.productservice.dto.request.ProductFIlterForm;
import com.ecommerce.productservice.dto.request.ProductRequest;
import com.ecommerce.productservice.dto.response.ProductResponse;
import com.ecommerce.productservice.service.IProductService;
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
@RequestMapping("api/v1/products")
@Validated
public class ProductController {

    private final IProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse<>(productService.getById(id),"Success"));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<List<ProductResponse>>> searchProduct(@Valid @RequestBody ProductFIlterForm form){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(productService.searchProduct(form), "Success"));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ProductResponse>> createProduct(@RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(productService.createProduct(request), "Success"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody @Valid ProductRequest productRequest, @PathVariable String id){
        productService.updateProduct(productRequest, id);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> deleteProduct(@PathVariable String id) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(productService.deleteProduct(id), "Success"));
    }
}
