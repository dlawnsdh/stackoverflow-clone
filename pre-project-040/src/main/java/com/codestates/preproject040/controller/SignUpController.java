package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.user.UserAccountDto;
import com.codestates.preproject040.dto.user.UserInfoDto;
import com.codestates.preproject040.dto.user.UserResponse;
import com.codestates.preproject040.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final UserAccountService userAccountService;

    @PostMapping("/join")
    public UserResponse registerUser(@RequestBody UserInfoDto request) {
        UserAccountDto dto = request.toDto();
        return userAccountService.saveUser(dto);
    }

}
