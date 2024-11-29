package com.xapp.member.authentication.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "USER_STATUS_HASH_LIST")
@Getter
@Setter
@Builder
public class UserStatusHashList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "USER_STATUS_KEY", length = 100, nullable = false)
    private String userStatusKey;

}
