package com.huykun.ecommercebe.serviceImp;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.huykun.ecommercebe.config.JwtConfig;
import com.huykun.ecommercebe.constant.DataConstant;
import com.huykun.ecommercebe.constant.account.AccountErrorMessage;
import com.huykun.ecommercebe.constant.account.AccountStatus;
import com.huykun.ecommercebe.constant.role.RoleErrorMessage;
import com.huykun.ecommercebe.constant.role.RoleName;
import com.huykun.ecommercebe.constant.validate.ValidationErrorMessage;
import com.huykun.ecommercebe.dto.LoginDTO;
import com.huykun.ecommercebe.dto.RegisterDTO;
import com.huykun.ecommercebe.entity.Account;
import com.huykun.ecommercebe.entity.Customer;
import com.huykun.ecommercebe.entity.Role;
import com.huykun.ecommercebe.exception.BadRequestException;
import com.huykun.ecommercebe.repository.AccountRepository;
import com.huykun.ecommercebe.repository.CustomerRepository;
import com.huykun.ecommercebe.repository.RoleRepository;
import com.huykun.ecommercebe.response.AccountResponse;
import com.huykun.ecommercebe.service.AccountService;
import com.huykun.ecommercebe.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

        private final AccountRepository accountRepository;
        private final CustomerRepository customerRepository;
        private final SecretKey secretKey;
        private final JwtConfig jwtConfig;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final ModelMapper modelMapper;
        private final RoleRepository roleRepository;

        @Override
        public AccountResponse register(RegisterDTO registerDTO) throws BadRequestException {
                Optional<Customer> customerCheck = customerRepository.findByPhoneNumber(registerDTO.getPhoneNumber());
                if (customerCheck.isPresent()) {
                        throw new BadRequestException(ValidationErrorMessage.PHONE_IS_EXIST);
                }
                Optional<Account> account = accountRepository.findByEmail(registerDTO.getEmail());
                if (account.isPresent()) {
                        throw new BadRequestException(ValidationErrorMessage.EMAIL_IS_EXIST);
                }
                Role role = roleRepository.findByName(RoleName.CUSTOMER)
                                .orElseThrow(() -> new BadRequestException(RoleErrorMessage.NOT_FOUND));

                AccountResponse responseDTO = new AccountResponse();

                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dob = LocalDate.parse(registerDTO.getDob().toString(), format);

                Account newAccount = Account.builder()
                                .email(registerDTO.getEmail())
                                .status(AccountStatus.ACTIVATED)
                                .provider(DataConstant.PROVIDER_LOCAL)
                                .role(role)
                                .password(passwordEncoder.encode(registerDTO.getPassword())).build();

                Customer customer = Customer.builder().firstName(registerDTO.getFirstName())
                                .lastName(registerDTO.getLastName()).dob(Date.valueOf(dob))
                                .gender(registerDTO.getGender()).phoneNumber(registerDTO.getPhoneNumber()).build();

                Account newAccountSaved = accountRepository.save(newAccount);
                customer.setAccount(newAccountSaved);
                Customer customerSaved = customerRepository.save(customer);

                Authentication authentication = new UsernamePasswordAuthenticationToken(registerDTO.getEmail(),
                                registerDTO.getPassword());
                Authentication authenticate = authenticationManager.authenticate(authentication);
                String token = Utils.buildJWT(authenticate, newAccount, secretKey, jwtConfig);
                responseDTO = modelMapper.map(customerSaved, AccountResponse.class);
                responseDTO.setRoleName(role.getName());
                responseDTO.setAccountId(newAccountSaved.getId());
                responseDTO.setDob(registerDTO.getDob().toString());
                responseDTO.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
                responseDTO.setGender(registerDTO.getGender());
                responseDTO.setPhoneNumber(registerDTO.getPhoneNumber());
                responseDTO.setToken(token);
                return responseDTO;
        }

        @Override
        public AccountResponse login(LoginDTO loginDTO) {
                Account account = accountRepository.findByEmail(loginDTO.getEmail())
                                .orElseThrow(() -> new BadRequestException(AccountErrorMessage.ACCOUNT_NOT_FOUND));
                Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                                loginDTO.getPassword());
                AccountResponse responseDTO = null;
                Authentication authenticate = authenticationManager.authenticate(authentication);
                if (authenticate.isAuthenticated()) {
                        String token = Utils.buildJWT(authenticate, account, secretKey, jwtConfig);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        responseDTO = AccountResponse.builder()
                                        .token(token)
                                        .accountId(account.getId())
                                        .email(account.getEmail())
                                        .status(account.getStatus())
                                        .provider(account.getProvider())
                                        .roleName(account.getRole().getName())
                                        .build();
                        if (account.getRole().getName().equalsIgnoreCase(RoleName.CUSTOMER)) {
                                Customer customer = account.getCustomer();
                                if (customer != null) {
                                        LocalDate dateFormat = LocalDate.parse(customer.getDob().toString(), format);
                                        responseDTO.setDob(dateFormat.toString());
                                        responseDTO.setFullName(customer.getFirstName() + " " + customer.getLastName());
                                        responseDTO.setGender(customer.getGender());
                                        responseDTO.setPhoneNumber(customer.getPhoneNumber());
                                }
                        }
                }
                return responseDTO;
        }

}
