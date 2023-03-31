package com.huykun.ecommercebe.service;

import java.util.List;

import com.huykun.ecommercebe.dto.AddressCreateDTO;
import com.huykun.ecommercebe.response.AddressResponse;

public interface AddressService {
    public AddressResponse createAddress(int customerId, AddressCreateDTO createDTO);

    public List<AddressResponse> getAllAddressByCustomer(int customerId);

    public AddressResponse getAddressById(int id);

    public AddressResponse updatAddressById(int id, AddressCreateDTO createDTO);

    public boolean deleteAddress(int id);
}
