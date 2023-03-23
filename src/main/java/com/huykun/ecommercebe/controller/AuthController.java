package com.huykun.ecommercebe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huykun.ecommercebe.constant.ResponseStatusConstant;
import com.huykun.ecommercebe.dto.LoginDTO;
import com.huykun.ecommercebe.dto.RegisterDTO;
import com.huykun.ecommercebe.exception.BadRequestException;
import com.huykun.ecommercebe.response.AccountResponse;
import com.huykun.ecommercebe.response.ResponseDTO;
import com.huykun.ecommercebe.serviceImp.AccountServiceImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final AccountServiceImp _accountServiceImp;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Validated @RequestBody RegisterDTO registerDTO)
            throws BadRequestException {
        ResponseDTO<AccountResponse> responseDTO = new ResponseDTO();
        AccountResponse registerResponseDto = _accountServiceImp.register(registerDTO);
        responseDTO.setData(registerResponseDto);
        responseDTO.setMessage("Register success");
        responseDTO.setStatus(ResponseStatusConstant.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Validated @RequestBody LoginDTO loginDTO) {
        ResponseDTO<AccountResponse> responseDTO = new ResponseDTO();
        AccountResponse loginResponseDTO = _accountServiceImp.login(loginDTO);
        responseDTO.setData(loginResponseDTO);
        responseDTO.setMessage("Login success");
        responseDTO.setStatus(ResponseStatusConstant.SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
