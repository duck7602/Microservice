package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.common.BaseResponse;
import com.ecommerce.authservice.dto.request.UserLoginRequest;
import com.ecommerce.authservice.dto.request.UserRegistrationRequest;
import com.ecommerce.authservice.dto.response.UserLoginResponse;
import com.ecommerce.authservice.dto.response.UserResponse;
import com.ecommerce.authservice.service.IUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> getUserById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse<>(userService.getUserById(id), "Success"));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<UserResponse>>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponse<>(userService.getAllUser(), "Success"));
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request){
        userService.createUser(request);
        return ResponseEntity.ok("Tạo người dùng thành công");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest){
        UserLoginResponse userLoginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(userLoginResponse);
    }
}
