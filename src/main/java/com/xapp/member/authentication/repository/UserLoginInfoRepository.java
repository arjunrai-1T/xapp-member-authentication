package com.xapp.member.authentication.repository;

import com.xapp.member.authentication.entity.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfo, String> {
    UserLoginInfo findByUserLoginId(String userLoginId);
}
