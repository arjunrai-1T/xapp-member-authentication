package com.xapp.member.authentication.controller;

import com.xapp.member.authentication.models.request.*;
import com.xapp.member.authentication.models.response.*;
import com.xapp.member.authentication.service.def.AuthenticationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final Logger logger= LoggerFactory.getLogger(AuthenticationController.class);

    private final String AuthenticationControllerName = "AuthenticationController";

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signInEndUser")
    public Mono<SignUpRes> doSignInEndUser(@Valid @RequestBody SignInReq req){
        logger.info("eapi {} entry",AuthenticationControllerName);
        logger.info("eapi {} exit",AuthenticationControllerName);
        return null;
    }

    @PostMapping("/signUpEndUser")
    public Mono<SignUpRes> doSignUpEndUser(@RequestBody SignUpReq req){
        logger.info("eapi {} entry",AuthenticationControllerName);
        Mono<SignUpRes> res = authenticationService.doSignUpImplEndUser(req);
        logger.info("eapi {} exit",AuthenticationControllerName);
        return res;
    }

    @PostMapping("/forgotPwdEndUser")
    public Mono<ForgotPwdRes> doForgotPwdEndUser(@RequestBody ForgotPwdReq req){
        return null;
    }

}
