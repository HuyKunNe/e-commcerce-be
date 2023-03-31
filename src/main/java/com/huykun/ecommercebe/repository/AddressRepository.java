package com.huykun.ecommercebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huykun.ecommercebe.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
    
}
