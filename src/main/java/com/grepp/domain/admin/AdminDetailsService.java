package com.grepp.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


import static com.grepp.global.Const.ADMIN;
import static com.grepp.global.Const.INVALID_ADMIN;

@RequiredArgsConstructor
@Service
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(INVALID_ADMIN));

        return new User(admin.getLoginId(), admin.getPassword(), authorities());
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return List.of(new SimpleGrantedAuthority(ADMIN)); // 권한 세팅
    }
}
