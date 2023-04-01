package com.huykun.ecommercebe.response.address;

import java.util.List;

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
public class ListAddressResponse {
    public Customer customer;
    public List<AddressResponse> addresses;
}
