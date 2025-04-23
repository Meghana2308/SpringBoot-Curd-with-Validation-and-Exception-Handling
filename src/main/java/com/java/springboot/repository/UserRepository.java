package com.java.springboot.repository;

import com.java.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findById(int id);

    List<User> findByName(String name);

    User findByEmail(String email);
}
