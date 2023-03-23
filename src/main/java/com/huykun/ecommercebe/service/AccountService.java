package com.huykun.ecommercebe.service;

import com.huykun.ecommercebe.dto.LoginDTO;
import com.huykun.ecommercebe.dto.RegisterDTO;
import com.huykun.ecommercebe.exception.BadRequestException;
import com.huykun.ecommercebe.response.AccountResponse;

public interface AccountService {

    public AccountResponse register(RegisterDTO registerDTO) throws BadRequestException;

    public AccountResponse login(LoginDTO loginDTO);
}
