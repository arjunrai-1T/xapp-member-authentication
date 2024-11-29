package com.xapp.member.authentication.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "USER_STATUS_HASH_LIST")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusHashList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "USER_STATUS_KEY", length = 100, nullable = false)
    private String userStatusKey;

}
