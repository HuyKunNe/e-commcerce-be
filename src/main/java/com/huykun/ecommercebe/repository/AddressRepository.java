package com.huykun.ecommercebe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huykun.ecommercebe.entity.Address;
import com.huykun.ecommercebe.entity.Customer;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAddressByCustomer(Customer customer);
}
