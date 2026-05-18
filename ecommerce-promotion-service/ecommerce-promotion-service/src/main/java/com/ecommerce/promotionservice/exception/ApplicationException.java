package com.ecommerce.promotionservice.exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message){
        super(message);
    }
}
