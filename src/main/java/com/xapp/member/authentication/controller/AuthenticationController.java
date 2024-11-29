package com.xapp.member.authentication.controller;

import com.xapp.member.authentication.models.request.*;
import com.xapp.member.authentication.models.response.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @PostMapping("/signin")
    public Mono<SignUpRes> doSignInEndUser(@Valid @RequestBody SignInReq req){
        return null;
    }

    @PostMapping("/signup")
    public Mono<SignUpRes> doSignUpEndUser(@RequestBody SignUpReq req){
        return null;
    }

    @PostMapping("/forgotpwd")
    public Mono<ForgotPwdRes> doForgotPwdEndUser(@RequestBody ForgotPwdReq req){
        return null;
    }
}
