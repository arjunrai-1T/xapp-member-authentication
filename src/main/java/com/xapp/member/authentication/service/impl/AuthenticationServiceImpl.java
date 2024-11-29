package com.xapp.member.authentication.service.impl;

import com.xapp.member.authentication.models.response.SignInRes;
import com.xapp.member.authentication.models.response.SignUpRes;
import com.xapp.member.authentication.service.def.AuthenticationService;
import reactor.core.publisher.Mono;

public class AuthenticationServiceImpl implements AuthenticationService {

    public Mono<SignUpRes> doSignIn(SignInRes req){
        return null;
    }
}
