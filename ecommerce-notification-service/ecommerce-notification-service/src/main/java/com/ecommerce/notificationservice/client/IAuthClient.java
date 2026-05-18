package com.ecommerce.notificationservice.client;

import com.ecommerce.notificationservice.client.dto.response.UserResponse;

import java.util.List;

public interface IAuthClient {
    List<UserResponse> getAllUser();
    UserResponse getUser(String id);
}
