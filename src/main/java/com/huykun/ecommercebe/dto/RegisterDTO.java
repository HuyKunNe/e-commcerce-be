package com.huykun.ecommercebe.dto;

import java.sql.Date;

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
public class RegisterDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private Date dob;
    private String password;
}
