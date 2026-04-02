package com.certitrack.backend.dto;

import com.certitrack.backend.entity.LoginHistory;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardResponse {
    private long totalRegisteredUsers;
    private long totalLoggedInUsers;
    private long totalLogins;
    private long totalCertificates;
    private long validCertificates;
    private long expiringCertificates;
    private long expiredCertificates;
    private List<LoginHistory> recentLogins;
}
