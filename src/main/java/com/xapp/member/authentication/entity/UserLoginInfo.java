package com.xapp.member.authentication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER_LOGIN_INFO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginInfo {

    @Id
    @Column(name = "PROFILE_ID", length = 10, nullable = false)
    private String profileId;

    @Column(name = "USER_LOGIN_ID", length = 200, nullable = false, unique = true)
    private String userLoginId;

    @Column(name = "USER_PWD", length = 512, nullable = false)
    private String userPwd;

    @ManyToOne
    @JoinColumn(name = "USER_STATUS_ID", referencedColumnName = "ID", nullable = false)
    private UserStatusHashList userStatus;

    @ManyToOne
    @JoinColumn(name = "USER_TYPE", referencedColumnName = "CATEGORY_NAME", nullable = false)
    private UserCategories userType;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean isDeleted;

    @Column(name = "CREATION_DATETIME", nullable = false)
    private LocalDateTime creationDatetime = LocalDateTime.now();

}
