package com.xapp.member.authentication.service.impl;

import com.xapp.member.authentication.configs.XAppConfig;
import com.xapp.member.authentication.entity.UserCategories;
import com.xapp.member.authentication.entity.UserLoginInfo;
import com.xapp.member.authentication.entity.UserStatusHashList;
import com.xapp.member.authentication.exceptions.ErrorResponseException;
import com.xapp.member.authentication.models.common.SearchOutputMeta;
import com.xapp.member.authentication.models.request.*;
import com.xapp.member.authentication.models.response.*;
import com.xapp.member.authentication.repository.UserCategoriesRepository;
import com.xapp.member.authentication.repository.UserLoginInfoRepository;
import com.xapp.member.authentication.repository.UserStatusHashListRepository;
import com.xapp.member.authentication.service.def.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.exception.ConstraintViolationException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserLoginInfoRepository userLoginInfoRepository;

    @Autowired
    UserCategoriesRepository userCategoriesRepository;

    @Autowired
    UserStatusHashListRepository userStatusHashListRepository;

    @Autowired
    private XAppConfig xAppConfig;

    private final Logger logger= LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final String AuthenticationServiceImplName = "AuthenticationServiceImpl";

    @Transactional
    public Mono<SignInRes> doSignInImplEndUser(SignInReq req){
        return null;
    }

    @Transactional
    public Mono<SignUpRes> doSignUpImplEndUser(SignUpReq req){

        logger.info("eapi service {} entry",AuthenticationServiceImplName);

        List<UserStatusHashList> userStatusHashList = userStatusHashListRepository.findAll().stream()
                .filter(userStatusHash -> userStatusHash.getUserStatusKey().equals(xAppConfig.USER_STATUS_ACTIVE))  // Filter by category name
                .toList();

        List<UserCategories> userCategoriesList = userCategoriesRepository.findAll().stream()
                                                  .filter(userCategory -> userCategory.getCategoryName().equals(xAppConfig.USER_TYPE_END_USER))  // Filter by category name
                                                  .toList();

        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime parsedDateTime = null;
        try {
            parsedDateTime = LocalDateTime.parse(formattedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException ignored) {
            logger.info("eapi service found creationDatetime {} with DateTimeParseException",AuthenticationServiceImplName);
            return Mono.error(new ErrorResponseException("User created failed with DateTimeParseException"));
        }
        UserLoginInfo userLoginInfo = UserLoginInfo.builder()
                .profileId(req.getSearchInputMeta().getCorrelationId())
                .userLoginId(req.getLoginId())
                .userPwd(req.getPassword())
                .userStatus(userStatusHashList.get(0))
                .userType(userCategoriesList.get(0))
                .isDeleted(false)
                .creationDatetime(parsedDateTime)
                .build();

        UserLoginInfo savedUserLoginInfo = null;
        try {
            savedUserLoginInfo = userLoginInfoRepository.save(userLoginInfo);
        }catch(DataIntegrityViolationException ex){
            logger.info("eapi service {} exit with DataIntegrityViolationException",AuthenticationServiceImplName);
            return Mono.error(new ErrorResponseException("User already exists"));
        }catch(ConstraintViolationException ex){
            logger.info("eapi service {} exit with ConstraintViolationException",AuthenticationServiceImplName);
            return Mono.error(new ErrorResponseException("User created failed with exception"));

        }catch(OptimisticLockException ex){
            logger.info("eapi service {} exit with OptimisticLockException",AuthenticationServiceImplName);
            return Mono.error(new ErrorResponseException("User created failed with exception"));
        }
        if (savedUserLoginInfo != null && savedUserLoginInfo.getProfileId() != null) {
            logger.info("eapi service {} exit with success",AuthenticationServiceImplName);
            logger.info("eapi service {} savedUserLoginInfo: {}",savedUserLoginInfo.toString());
            SignUpRes signUpRes = (SignUpRes) SignUpRes.builder()
                    .searchOutputMeta(SearchOutputMeta.builder().correlationId(req.getSearchInputMeta().getCorrelationId()).build())
                    .status("success") // status
                    .message("User created successfully") // message
                    .build();
            return Mono.just(signUpRes);
        } else {
            logger.info("eapi service {} exit with error",AuthenticationServiceImplName);
            SignUpRes signUpRes = SignUpRes.builder()
                    .searchOutputMeta(SearchOutputMeta.builder().correlationId(req.getSearchInputMeta().getCorrelationId()).build())
                    .status("fail") // status
                    .message("User created failed") // message
                    .build();
            return Mono.just(signUpRes);
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
