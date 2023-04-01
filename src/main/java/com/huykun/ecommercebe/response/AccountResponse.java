package com.huykun.ecommercebe.response;

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
public class AccountResponse {

    private String token;
    private int accountId;
    private int customerId;
    private String email;
    private String fullName;
    private String gender;
    private String dob;
    private String phoneNumber;
    private String status;
    private String provider;
    private String roleName;
}