package com.ecommerce.authservice.service;


import com.ecommerce.authservice.dto.request.UserLoginRequest;
import com.ecommerce.authservice.dto.request.UserRegistrationRequest;
import com.ecommerce.authservice.dto.response.UserLoginResponse;
import com.ecommerce.authservice.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    void createUser(UserRegistrationRequest request);

    UserLoginResponse login(UserLoginRequest loginRequest);

    UserResponse getUserById(String id);

    List<UserResponse> getAllUser();
}
