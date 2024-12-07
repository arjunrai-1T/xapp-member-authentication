package com.xapp.member.authentication.repository;

import com.xapp.member.authentication.entity.UserSessionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionKeyRepository extends JpaRepository<UserSessionKey, Integer> {
}
