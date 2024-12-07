package com.xapp.member.authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_sessions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserSession {

    @Id
    @Column(name = "user_login_id",nullable = false,length = 255)
    private String userLoginId;

    @Column(name = "session_token", nullable = false, length = 400)
    private String sessionToken;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    @Column(name = "login_datetime", nullable = false)
    private LocalDateTime loginDatetime;

}