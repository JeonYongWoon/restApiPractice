package com.example.restapipractice.admin.repository;

import com.example.restapipractice.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);

    Optional<Admin> findByEmail(String email);
}
