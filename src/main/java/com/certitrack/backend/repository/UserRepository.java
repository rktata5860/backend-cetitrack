package com.certitrack.backend.repository;

import com.certitrack.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByLoginCountGreaterThan(Integer loginCount);
    long countByLoginCountGreaterThan(Integer loginCount);
}
