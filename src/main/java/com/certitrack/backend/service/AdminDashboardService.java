package com.certitrack.backend.service;

import com.certitrack.backend.dto.DashboardResponse;
import com.certitrack.backend.entity.CertificateStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final UserService userService;
    private final LoginHistoryService loginHistoryService;
    private final CertificateService certificateService;

    public DashboardResponse getDashboardStats() {
        return DashboardResponse.builder()
                .totalRegisteredUsers(userService.getRegisteredUserCount())
                .totalLoggedInUsers(userService.getLoggedInUsersCount())
                .totalLogins(loginHistoryService.getTotalLogins())
                .totalCertificates(certificateService.getTotalCertificates())
                .validCertificates(certificateService.getCertificateCountByStatus(CertificateStatus.VALID))
                .expiringCertificates(certificateService.getCertificateCountByStatus(CertificateStatus.EXPIRING))
                .expiredCertificates(certificateService.getCertificateCountByStatus(CertificateStatus.EXPIRED))
                .recentLogins(loginHistoryService.getRecentLogins())
                .build();
    }
}
