package com.huykun.ecommercebe.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.huykun.ecommercebe.constant.address.AddressErrorMessage;
import com.huykun.ecommercebe.constant.customer.CustomerErrorMessage;
import com.huykun.ecommercebe.dto.AddressCreateDTO;
import com.huykun.ecommercebe.entity.Address;
import com.huykun.ecommercebe.entity.Customer;
import com.huykun.ecommercebe.exception.BadRequestException;
import com.huykun.ecommercebe.exception.ListEmptyException;
import com.huykun.ecommercebe.repository.AddressRepository;
import com.huykun.ecommercebe.repository.CustomerRepository;
import com.huykun.ecommercebe.response.address.AddressResponse;
import com.huykun.ecommercebe.response.address.AddressSingleResponse;
import com.huykun.ecommercebe.response.address.ListAddressResponse;
import com.huykun.ecommercebe.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddressSingleResponse createAddress(int customerId, AddressCreateDTO createDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BadRequestException(CustomerErrorMessage.NOT_FOUND));
        Address address = Address.builder()
                .name(createDTO.getName())
                .company(createDTO.getCompany())
                .nationality(createDTO.getNationality())
                .city(createDTO.getCity())
                .district(createDTO.getDistrict())
                .ward(createDTO.getWard())
                .street(createDTO.getStreet())
                .no(createDTO.getNo())
                .customer(customer)
                .phoneNumber(createDTO.getPhoneNumber())
                .build();
        Address addressSaved = addressRepository.save(address);
        AddressSingleResponse responseDTO = new AddressSingleResponse();
        responseDTO = modelMapper.map(addressSaved, AddressSingleResponse.class);
        return responseDTO;
    }

    @Override
    public ListAddressResponse getAllAddressByCustomer(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BadRequestException(CustomerErrorMessage.NOT_FOUND));
        ListAddressResponse responseDTO = new ListAddressResponse();
        List<Address> addresses = addressRepository.findAddressByCustomer(customer);
        if (addresses.size() != 0) {
            List<AddressResponse> listAddressResponses = new ArrayList<AddressResponse>();
            for (Address address : addresses) {
                AddressResponse addressResponse = new AddressResponse();
                addressResponse = modelMapper.map(address, AddressResponse.class);
                listAddressResponses.add(addressResponse);
            }
            responseDTO.setAddresses(listAddressResponses);
            responseDTO.setCustomer(customer);
        } else {
            throw new ListEmptyException(AddressErrorMessage.LIST_EMPTY);
        }
        return responseDTO;
    }

    @Override
    public AddressSingleResponse getAddressById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAddressById'");
    }

    @Override
    public AddressSingleResponse updatAddressById(int id, AddressCreateDTO createDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatAddressById'");
    }

    @Override
    public boolean deleteAddress(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAddress'");
    }
}
