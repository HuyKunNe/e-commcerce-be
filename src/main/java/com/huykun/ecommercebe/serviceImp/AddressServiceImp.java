package com.huykun.ecommercebe.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.huykun.ecommercebe.dto.AddressCreateDTO;
import com.huykun.ecommercebe.repository.AddressRepository;
import com.huykun.ecommercebe.repository.CustomerRepository;
import com.huykun.ecommercebe.response.AddressResponse;
import com.huykun.ecommercebe.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AddressResponse createAddress(int customerId, AddressCreateDTO createDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAddress'");
    }

    @Override
    public List<AddressResponse> getAllAddressByCustomer(int customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllAddressByCustomer'");
    }

    @Override
    public AddressResponse getAddressById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAddressById'");
    }

    @Override
    public AddressResponse updatAddressById(int id, AddressCreateDTO createDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatAddressById'");
    }

    @Override
    public boolean deleteAddress(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAddress'");
    }

}
