package com.certitrack.backend.repository;

import com.certitrack.backend.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    List<LoginHistory> findAllByOrderByLoginTimeDesc();
    List<LoginHistory> findTop10ByOrderByLoginTimeDesc();
}
