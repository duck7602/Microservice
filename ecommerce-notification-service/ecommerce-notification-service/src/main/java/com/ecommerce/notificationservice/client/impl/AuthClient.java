package com.ecommerce.notificationservice.client.impl;

import com.ecommerce.notificationservice.client.IAuthClient;
import com.ecommerce.notificationservice.client.dto.response.UserResponse;
import com.ecommerce.notificationservice.common.BaseResponse;
import com.ecommerce.notificationservice.exception.ApplicationException;
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
public class AuthClient implements IAuthClient {
    private final WebClient.Builder webClient;
    @Value("${client.auth.uri}")
    private String authUri;


    @Override
    public List<UserResponse> getAllUser() {
        BaseResponse<List<UserResponse>> userResponses  = webClient.build()
                .get()
                .uri(authUri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<List<UserResponse>>>() {
                })
                .block();
        if (userResponses == null) {
            throw new ApplicationException("Call Auth_Service Error");
        }
        return userResponses.getData();
    }

    @Override
    public UserResponse getUser(String id) {
        BaseResponse<UserResponse> userResponse  = webClient.build()
                .get()
                .uri(authUri+"/"+ id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<UserResponse>>() {
                })
                .block();
        if (userResponse == null) {
            throw new ApplicationException("Call Auth_Service Error");
        }
        return userResponse.getData();
    }
}
