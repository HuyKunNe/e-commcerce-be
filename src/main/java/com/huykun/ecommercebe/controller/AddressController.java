package com.huykun.ecommercebe.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huykun.ecommercebe.constant.ResponseStatusConstant;
import com.huykun.ecommercebe.dto.AddressCreateDTO;
import com.huykun.ecommercebe.exception.BadRequestException;
import com.huykun.ecommercebe.exception.ListEmptyException;
import com.huykun.ecommercebe.response.ResponseDTO;
import com.huykun.ecommercebe.response.address.AddressSingleResponse;
import com.huykun.ecommercebe.response.address.ListAddressResponse;
import com.huykun.ecommercebe.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService _addressService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAddress(@RequestParam int id, @RequestBody AddressCreateDTO createDTO)
            throws BadRequestException {
        ResponseDTO<AddressSingleResponse> responseDTO = new ResponseDTO();
        AddressSingleResponse addressResponse = _addressService.createAddress(id, createDTO);
        responseDTO.setData(addressResponse);
        responseDTO.setMessage("Create Address success");
        responseDTO.setStatus(ResponseStatusConstant.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/getByCustomer")
    public ResponseEntity<ResponseDTO> getByCustomer(@RequestParam int id) throws ListEmptyException {
        ResponseDTO<ListAddressResponse> responseDTO = new ResponseDTO();
        ListAddressResponse addressResponse = _addressService.getAllAddressByCustomer(id);
        responseDTO.setData(addressResponse);
        responseDTO.setMessage("Get all Address by customer successfully");
        responseDTO.setStatus(ResponseStatusConstant.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
