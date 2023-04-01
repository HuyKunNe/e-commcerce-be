package com.huykun.ecommercebe.response.address;

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
    private String name;
    private String company;
    private String nationality;
    private String city;
    private String district;
    private String ward;
    private String street;
    private String no;
    private String phoneNumber;
}
