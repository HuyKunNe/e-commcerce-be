package com.huykun.ecommercebe.dto;

import javax.validation.constraints.Email;

import com.huykun.ecommercebe.constant.validate.ValidationErrorMessage;

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
public class LoginDTO {

    @Email(message = ValidationErrorMessage.EMAIL_VALID_MESSAGE_WHEN_LOGIN)
    private String email;
    private String password;
}