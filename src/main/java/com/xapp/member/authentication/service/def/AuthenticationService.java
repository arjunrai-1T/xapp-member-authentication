package com.xapp.member.authentication.service.def;

import com.xapp.member.authentication.models.request.*;
import com.xapp.member.authentication.models.response.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
    public Mono<SignInRes> doSignInImplEndUser(SignInReq req, WebSession webSession);
    public Mono<SignUpRes> doSignUpImplEndUser(SignUpReq req);
    public Mono<ForgotPwdRes> doForgotPwdImplEndUser(ForgotPwdReq req);
    public Mono<SignUpRes> doSignUpImplAdmin(SignUpReq req);
}
