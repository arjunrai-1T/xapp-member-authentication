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
    public Mono<SignupRes> doSignIn(@Valid @RequestBody SigninReq req){
        return null;
    }

    @PostMapping("/signup")
    public Mono<SignupRes> doSignIn(@RequestBody SignupReq req){
        return null;
    }

    @PostMapping("/forgotpwd")
    public Mono<ForgotPwdRes> doSignIn(@RequestBody ForgotPwdReq req){
        return null;
    }
}
