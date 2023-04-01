package com.huykun.ecommercebe.service;

import com.huykun.ecommercebe.dto.AddressCreateDTO;
import com.huykun.ecommercebe.response.address.AddressSingleResponse;
import com.huykun.ecommercebe.response.address.ListAddressResponse;

public interface AddressService {
    public AddressSingleResponse createAddress(int customerId, AddressCreateDTO createDTO);

    public ListAddressResponse getAllAddressByCustomer(int customerId);

    public AddressSingleResponse getAddressById(int id);

    public AddressSingleResponse updatAddressById(int id, AddressCreateDTO createDTO);

    public boolean deleteAddress(int id);
}
