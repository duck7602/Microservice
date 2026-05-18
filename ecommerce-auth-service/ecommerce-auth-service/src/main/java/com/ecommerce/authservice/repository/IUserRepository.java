package com.ecommerce.authservice.repository;


import com.ecommerce.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {
}
