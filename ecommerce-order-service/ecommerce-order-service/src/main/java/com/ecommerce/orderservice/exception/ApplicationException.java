package com.ecommerce.orderservice.exception;

public class ApplicationException extends RuntimeException{
        public ApplicationException(String mesage){
            super(mesage);
        }
}
