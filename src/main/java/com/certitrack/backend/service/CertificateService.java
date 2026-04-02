package com.certitrack.backend.service;

import com.certitrack.backend.entity.Certificate;
import com.certitrack.backend.entity.CertificateStatus;
import com.certitrack.backend.exception.ResourceNotFoundException;
import com.certitrack.backend.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public Certificate createCertificate(Certificate certificate) {
        if (certificate.getStatus() == null) {
            certificate.setStatus(CertificateStatus.VALID);
        }
        return certificateRepository.save(certificate);
    }

    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public Certificate getCertificateById(Long id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id: " + id));
    }

    public Certificate updateCertificate(Long id, Certificate updatedCertificate) {
        Certificate existing = getCertificateById(id);

        existing.setUserId(updatedCertificate.getUserId());
        existing.setTitle(updatedCertificate.getTitle());
        existing.setIssuer(updatedCertificate.getIssuer());
        existing.setCategory(updatedCertificate.getCategory());
        existing.setIssueDate(updatedCertificate.getIssueDate());
        existing.setExpiryDate(updatedCertificate.getExpiryDate());
        existing.setCredentialId(updatedCertificate.getCredentialId());
        existing.setCredentialUrl(updatedCertificate.getCredentialUrl());
        existing.setNotes(updatedCertificate.getNotes());
        existing.setStatus(updatedCertificate.getStatus());

        return certificateRepository.save(existing);
    }

    public void deleteCertificate(Long id) {
        Certificate certificate = getCertificateById(id);
        certificateRepository.delete(certificate);
    }

    public List<Certificate> getCertificatesByUserId(Long userId) {
        return certificateRepository.findByUserId(userId);
    }

    public long getTotalCertificates() {
        return certificateRepository.count();
    }

    public long getCertificateCountByStatus(CertificateStatus status) {
        return certificateRepository.countByStatus(status);
    }
}
