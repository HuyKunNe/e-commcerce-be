package com.huykun.ecommercebe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huykun.ecommercebe.entity.Account;
import com.huykun.ecommercebe.entity.Role;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    public Optional<Account> findByEmail(String email);

    public boolean existsByRole(Role role);

}
