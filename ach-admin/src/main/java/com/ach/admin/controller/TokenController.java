package com.ach.admin.controller;


import com.ach.admin.customize.securityService.TokenService;
import com.ach.common.base.BaseResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("unrepeatable")
    public BaseResponseData<String> getUnrepeatableToken() {
        //生成防重复提交token
        String s = tokenService.generateUnrepeatableToken();
        return BaseResponseData.ok(s);
    }
}
