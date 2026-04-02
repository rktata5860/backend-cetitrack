package com.certitrack.backend.controller;

import com.certitrack.backend.dto.ApiResponse;
import com.certitrack.backend.entity.Certificate;
import com.certitrack.backend.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public ResponseEntity<ApiResponse<Certificate>> createCertificate(@RequestBody Certificate certificate) {
        Certificate created = certificateService.createCertificate(certificate);
        return ResponseEntity.ok(new ApiResponse<>("Certificate created successfully", created));
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        return ResponseEntity.ok(certificateService.getAllCertificates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
        return ResponseEntity.ok(certificateService.getCertificateById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Certificate>> updateCertificate(@PathVariable Long id, @RequestBody Certificate certificate) {
        Certificate updated = certificateService.updateCertificate(id, certificate);
        return ResponseEntity.ok(new ApiResponse<>("Certificate updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCertificate(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.ok(new ApiResponse<>("Certificate deleted successfully", "Deleted id: " + id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Certificate>> getCertificatesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(certificateService.getCertificatesByUserId(userId));
    }
}
