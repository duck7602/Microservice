package com.ecommerce.inventoryservice.exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message){
        super(message);
    }
}
