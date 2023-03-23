package com.huykun.ecommercebe.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.huykun.ecommercebe.entity.Account;
import com.huykun.ecommercebe.repository.AccountRepository;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
@Builder
public class ApplicationUserService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (!account.isPresent()) {
            throw new IllegalStateException("Employee Not Found");
        }
        return new UserDetail(account.get());
    }
}
