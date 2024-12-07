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
@ToString
public class UserStatusHashList {

    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Id
    @Column(name = "USER_STATUS", length = 100, nullable = false)
    private String userStatusKey;

}
