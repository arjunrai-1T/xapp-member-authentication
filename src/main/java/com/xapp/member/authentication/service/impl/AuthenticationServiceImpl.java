package com.xapp.member.authentication.service.impl;

import com.xapp.member.authentication.configs.XAppConfig;
import com.xapp.member.authentication.entity.*;
import com.xapp.member.authentication.exceptions.ErrorResponseException;
import com.xapp.member.authentication.models.common.SearchOutputMeta;
import com.xapp.member.authentication.models.request.*;
import com.xapp.member.authentication.models.response.*;
import com.xapp.member.authentication.repository.*;
import com.xapp.member.authentication.service.def.AuthenticationService;
import com.xapp.member.authentication.utility.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.exception.ConstraintViolationException;
import com.xapp.member.authentication.utility.HashGenerator;

import javax.crypto.SecretKey;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserLoginInfoRepository userLoginInfoRepository;

    @Autowired
    UserCategoriesRepository userCategoriesRepository;

    @Autowired
    UserStatusHashListRepository userStatusHashListRepository;

    @Autowired
    UserSessionRepository userSessionRepository;

    @Autowired
    UserSessionKeyRepository userSessionKeyRepository;

    @Autowired
    private XAppConfig xAppConfig;

    @Autowired
    JwtToken jwtToken;

    private final Logger logger= LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final String AuthenticationServiceImplName = "AuthenticationServiceImpl";

    @Transactional
    public Mono<SignInRes> doSignInImplEndUser(SignInReq req , WebSession webSession){

        List<UserLoginInfo> userLoginList = null;
        userLoginList = userLoginInfoRepository.findAll().stream()
                .filter(user -> user.getUserLoginId().equalsIgnoreCase(req.getUserid()))  // Filter by user login id
                .toList();
        if(null == userLoginList || userLoginList.size() == 0){
            return Mono.just(SignInRes.builder()
                    .searchOutputMeta(SearchOutputMeta.builder().correlationId(req.getSearchInputMeta().getCorrelationId()).build())
                    .status("fail")
                    .message("user not found")
                    .sessionToken("")
                    .loginDateTime(String.valueOf(""))
                    .logoutDateTime(String.valueOf(""))
                    .build());
        }
        UserLoginInfo userLoginInfo = userLoginList.get(0);
        String storedHash = userLoginInfo.getUserPwd();
        String storedSalt = userLoginInfo.getUserPwdSalt();
        String algoName = "SHA-256";
        String enteredPassword = req.getPassword();
        boolean isMatched = HashGenerator.verifyPassword(enteredPassword, storedHash,  storedSalt,  algoName);
        if(isMatched){
            //get secret key from database
            List<UserSessionKey> userSessionKeyList = null;
            userSessionKeyList = userSessionKeyRepository.findAll().stream().toList();
            logger.info("eapi {} secret key: {}",AuthenticationServiceImplName,userSessionKeyList.get(0).getSecretKey());
            SecretKey secretKey    = jwtToken.createSecretKey(userSessionKeyList.get(0).getSecretKey());
            String    sessionToken = jwtToken.createToken(secretKey,req.getUserid(),"","login session");
            //Login Date & Time
            String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime parsedLogInDateTime = null;
            try {
                parsedLogInDateTime = LocalDateTime.parse(formattedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException ignored) {
                logger.info("eapi service found creationDatetime {} with parsedLogInDateTime(DateTimeParseException)",AuthenticationServiceImplName);
                return Mono.error(new ErrorResponseException("User login failed with DateTimeParseException"));
            }
            //Logout Date & Time
            formattedDateTime = LocalDateTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime parsedLogoutDateTime = null;
            try {
                parsedLogoutDateTime = LocalDateTime.parse(formattedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException ignored) {
                logger.info("eapi service found creationDatetime {} with parsedLogoutDateTime(DateTimeParseException)",AuthenticationServiceImplName);
                return Mono.error(new ErrorResponseException("User login failed with DateTimeParseException"));
            }
            //Store session token
            UserSession userSession = UserSession.builder()
                                        .userLoginId(req.getUserid())
                                        .sessionToken(sessionToken)
                                        .expirationTime(parsedLogoutDateTime)
                                        .loginDatetime(parsedLogInDateTime)
                                        .build();
            userSessionRepository.save(userSession);
            // Store userId and authToken in the webSession
            webSession.getAttributes().put("userId", req.getUserid());
            webSession.getAttributes().put("sessionToken", sessionToken);
            webSession.getAttributes().put("loginDateTime", parsedLogInDateTime);
            webSession.getAttributes().put("logoutDateTime", parsedLogoutDateTime);
            return Mono.just(SignInRes.builder()
                     .searchOutputMeta(SearchOutputMeta.builder().correlationId(req.getSearchInputMeta().getCorrelationId()).build())
                     .status("success")
                     .message("user login success")
                     .sessionToken(sessionToken)
                     .loginDateTime(String.valueOf(parsedLogInDateTime))
                     .logoutDateTime(String.valueOf(parsedLogoutDateTime))
                    .build());
        }else{
            return Mono.just(SignInRes.builder()
                    .searchOutputMeta(SearchOutputMeta.builder().correlationId(req.getSearchInputMeta().getCorrelationId()).build())
                    .status("fail")
                    .message("user login failed")
                    .sessionToken("")
                    .loginDateTime(String.valueOf(""))
                    .logoutDateTime(String.valueOf(""))
                    .build());
        }
    }

    @Transactional
    public Mono<SignUpRes> doSignUpImplEndUser(SignUpReq req){

        logger.info("eapi service {} entry",AuthenticationServiceImplName);

        UserLoginInfo savedUserLoginInfo = null;

        List<UserLoginInfo> usserLoginList = userLoginInfoRepository.findAll().stream()
                .filter(user -> user.getUserLoginId().equalsIgnoreCase(req.getLoginId()))  // Filter by category name
                .toList();

        if(!usserLoginList.isEmpty()){
            logger.info("eapi service found creationDatetime {} with DateTimeParseException",AuthenticationServiceImplName);
            SignUpRes signUpRes = SignUpRes.builder()
                    .searchOutputMeta(SearchOutputMeta.builder().correlationId(req.getSearchInputMeta().getCorrelationId()).build())
                    .status("fail") // status
                    .message("User already exist") // message
                    .build();
            return Mono.just(signUpRes);
        }

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
        String completeUserInfo = req.getLoginId()+" "+req.getPassword()+userCategoriesList.get(0).getCategoryName();
        String profileId ="PF" + HashGenerator.generateWEPKeyFromString(completeUserInfo, 16);
        if(profileId.length()> 30 ){
            profileId = profileId.substring(0, 30);
        }
        String pwdSalt = HashGenerator.generateSalt();
        String pwdHash = HashGenerator.generateHashWithSalt(req.getPassword(),pwdSalt,"SHA-256");
        UserLoginInfo userLoginInfo = UserLoginInfo.builder()
                .profileId(profileId)
                .userLoginId(req.getLoginId())
                .userPwdSalt(pwdSalt)
                .userPwd(pwdHash)
                .userStatus(userStatusHashList.get(0))
                .userType(userCategoriesList.get(0))
                .isDeleted(false)
                .creationDatetime(parsedDateTime)
                .build();

        try {
            savedUserLoginInfo = userLoginInfoRepository.save(userLoginInfo);
        }catch(DataIntegrityViolationException ex){
            logger.info("eapi service {} exit with DataIntegrityViolationException {}",AuthenticationServiceImplName,ex.getMessage());
            return Mono.error(new ErrorResponseException("User already exists"));
        }catch(ConstraintViolationException ex){
            logger.info("eapi service {} exit with ConstraintViolationException {}",AuthenticationServiceImplName,ex.getMessage());
            return Mono.error(new ErrorResponseException("User created failed with exception"));

        }catch(OptimisticLockException ex){
            logger.info("eapi service {} exit with OptimisticLockException {}",AuthenticationServiceImplName,ex.getMessage());
            return Mono.error(new ErrorResponseException("User created failed with exception"));
        }catch(org.springframework.orm.jpa.JpaSystemException ex){
            logger.info("eapi service {} exit with JpaSystemException {}",AuthenticationServiceImplName,ex.getMessage());
            return Mono.error(new ErrorResponseException("User created failed with exception"));
        }
        if (savedUserLoginInfo != null && savedUserLoginInfo.getProfileId() != null) {
            logger.info("eapi service {} exit with success",AuthenticationServiceImplName);
            logger.info("eapi service {} savedUserLoginInfo: {}",(savedUserLoginInfo.toString()));
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
