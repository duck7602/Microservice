package com.ecommerce.orderservice.client.impl;

import com.ecommerce.orderservice.client.IIventoryClient;
import com.ecommerce.orderservice.client.dto.request.InventoryFilterForm;
import com.ecommerce.orderservice.client.dto.response.InventoryResponse;
import com.ecommerce.orderservice.common.BaseResponse;
import com.ecommerce.orderservice.exception.ApplicationException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Component
@Data
@RequiredArgsConstructor
public class IventoryClient implements IIventoryClient {

    private final WebClient.Builder webClient;

    @Value("${client.inventory.uri}")
    private String inventoryUri;

    @Override
    public List<InventoryResponse> getIventory(InventoryFilterForm form) {
        BaseResponse<List<InventoryResponse>> response = webClient.build()
                .post()
                .uri(inventoryUri)
                .bodyValue(form)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<List<InventoryResponse>>>() {})
                .block();

        if(response == null){
            throw new ApplicationException("Không thể kết nối đến Inventory");
        }
        return response.getData();
    }
}
