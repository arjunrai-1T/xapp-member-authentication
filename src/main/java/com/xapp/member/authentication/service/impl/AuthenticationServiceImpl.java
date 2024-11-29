package com.xapp.member.authentication.service.impl;

import com.xapp.member.authentication.configs.XAppConfig;
import com.xapp.member.authentication.entity.UserCategories;
import com.xapp.member.authentication.entity.UserLoginInfo;
import com.xapp.member.authentication.entity.UserStatusHashList;
import com.xapp.member.authentication.models.common.SearchOutputMeta;
import com.xapp.member.authentication.models.request.*;
import com.xapp.member.authentication.models.response.*;
import com.xapp.member.authentication.repository.UserCategoriesRepository;
import com.xapp.member.authentication.repository.UserLoginInfoRepository;
import com.xapp.member.authentication.repository.UserStatusHashListRepository;
import com.xapp.member.authentication.service.def.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserLoginInfoRepository userLoginInfoRepository;

    @Autowired
    UserCategoriesRepository userCategoriesRepository;

    @Autowired
    UserStatusHashListRepository userStatusHashListRepository;

    @Autowired
    private XAppConfig xAppConfig;

    @Transactional
    public Mono<SignInRes> doSignInImplEndUser(SignInReq req){
        return null;
    }

    @Transactional
    public Mono<SignUpRes> doSignUpImplEndUser(SignUpReq req){

        List<UserStatusHashList> userStatusHashList = userStatusHashListRepository.findAll().stream()
                .filter(userStatusHash -> userStatusHash.getUserStatusKey().equals(xAppConfig.USER_TYPE_END_USER))  // Filter by category name
                .toList();

        List<UserCategories> userCategoriesList = userCategoriesRepository.findAll().stream()
                                                  .filter(userCategory -> userCategory.getCategoryName().equals(xAppConfig.USER_TYPE_END_USER))  // Filter by category name
                                                  .toList();

        UserLoginInfo userLoginInfo = UserLoginInfo.builder()
                .profileId("Test")
                .userLoginId(req.getLoginId())
                .userPwd(req.getPassword())
                .userStatus(userStatusHashList.get(0))
                .userType(userCategoriesList.get(0))
                .isDeleted(false)
                .creationDatetime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .build();
        UserLoginInfo savedUserLoginInfo = userLoginInfoRepository.save(userLoginInfo);
        if (savedUserLoginInfo != null && savedUserLoginInfo.getProfileId() != null) {
            return Mono.just((SignUpRes) SignUpRes.builder()
                            .searchOutputMeta(SearchOutputMeta.builder().correlationId(req.getSearchInputMeta().getCorrelationId()).build())
                            .status("success") // status
                            .message("User created successfully") // message
                            .build());
        } else {
            return Mono.just((SignUpRes) SignUpRes.builder()
                    .searchOutputMeta(SearchOutputMeta.builder().correlationId(req.getSearchInputMeta().getCorrelationId()).build())
                    .status("fail") // status
                    .message("User created failed") // message
                    .build());
        }
    }

    @Transactional
    public Mono<ForgotPwdRes> doForgotPwdImplEndUser(ForgotPwdReq req){
        return null;
    }

    @Transactional
    public Mono<SignUpRes> doSignUpImplAdmin(SignUpReq req){
        return null;
    }

}
