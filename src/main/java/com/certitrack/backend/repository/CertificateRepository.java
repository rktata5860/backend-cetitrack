package com.certitrack.backend.repository;

import com.certitrack.backend.entity.Certificate;
import com.certitrack.backend.entity.CertificateStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByUserId(Long userId);
    long countByStatus(CertificateStatus status);
}
