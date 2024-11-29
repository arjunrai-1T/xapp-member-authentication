package com.xapp.member.authentication.service.def;

import com.xapp.member.authentication.models.response.SignInRes;
import com.xapp.member.authentication.models.response.SignUpRes;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
    public Mono<SignUpRes> doSignIn(SignInRes req);
}
