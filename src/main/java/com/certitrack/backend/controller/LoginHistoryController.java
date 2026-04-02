package com.certitrack.backend.controller;

import com.certitrack.backend.entity.LoginHistory;
import com.certitrack.backend.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logins")
@RequiredArgsConstructor
public class LoginHistoryController {

    private final LoginHistoryService loginHistoryService;

    @GetMapping
    public ResponseEntity<List<LoginHistory>> getAllLoginHistory() {
        return ResponseEntity.ok(loginHistoryService.getAllLoginHistory());
    }
}
