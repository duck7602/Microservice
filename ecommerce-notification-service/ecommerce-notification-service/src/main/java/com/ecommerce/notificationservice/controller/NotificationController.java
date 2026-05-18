package com.ecommerce.notificationservice.controller;

import com.ecommerce.notificationservice.common.BaseResponse;
import com.ecommerce.notificationservice.dto.request.SendMailForm;
import com.ecommerce.notificationservice.dto.response.NotificationResponse;
import com.ecommerce.notificationservice.service.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/notifications")
@Validated
public class NotificationController {

    private final INotificationService notificationService;

    @GetMapping()
    public ResponseEntity<BaseResponse<List<NotificationResponse>>> getAllPromotion(){
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(notificationService.getAllPromotion(), "Success"));
    }

    @PostMapping()
    public ResponseEntity<String> sendMailById(@RequestBody SendMailForm form){
        notificationService.sendOrderSuccessEmail(form.getEmail(), form.getTotalAmount());
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
