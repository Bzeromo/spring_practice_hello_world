package com.example.helloworld.user.repository;

import com.example.helloworld.user.domain.User3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository5 extends JpaRepository<User3, String> {
    Optional<User3> findByEmail(String email);
    boolean existsByEmail(String email);
}
