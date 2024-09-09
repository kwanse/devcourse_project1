package com.grepp.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin login(String loginId, String password) {
        return adminRepository.findByLoginIdAndPassword(loginId, password);
    }
}
