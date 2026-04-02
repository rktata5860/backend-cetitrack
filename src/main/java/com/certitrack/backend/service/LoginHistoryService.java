package com.certitrack.backend.service;

import com.certitrack.backend.entity.LoginHistory;
import com.certitrack.backend.repository.LoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    public List<LoginHistory> getAllLoginHistory() {
        return loginHistoryRepository.findAllByOrderByLoginTimeDesc();
    }

    public List<LoginHistory> getRecentLogins() {
        return loginHistoryRepository.findTop10ByOrderByLoginTimeDesc();
    }

    public long getTotalLogins() {
        return loginHistoryRepository.count();
    }
}
