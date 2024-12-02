package com.xapp.member.authentication.repository;

import com.xapp.member.authentication.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
}
