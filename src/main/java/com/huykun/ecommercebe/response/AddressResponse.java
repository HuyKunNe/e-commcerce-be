package com.huykun.ecommercebe.response;

import com.huykun.ecommercebe.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
    private int id;
    private String company;
    private String nationality;
    private String city;
    private String district;
    private String street;
    private String apartment;
    private String phoneNumber;
    private Customer customer;
    private String status;
}
