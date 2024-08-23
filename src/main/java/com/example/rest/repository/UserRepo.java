package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.model.User;

public interface UserRepo  extends JpaRepository<User, Integer> {
             
}
