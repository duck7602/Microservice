package com.ecommerce.promotionservice.exception;

import com.ecommerce.promotionservice.common.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var listErrors = e.getBindingResult().getFieldErrors();
        List<String> message = new ArrayList<>();

        for (var error : listErrors) {
            message.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseResponse<>(null, String.join(", ", message)));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<BaseResponse<Object>> handleApplicationException(ApplicationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseResponse<>(null, e.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<BaseResponse<Object>> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseResponse<>(null, "Xảy ra lỗi, vui lòng thử lại" + e.getMessage()));
    }
}
