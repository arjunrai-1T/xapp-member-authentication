package com.xapp.member.authentication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_SESSION_KEY")// Maps to the USER_SESSION_KEY table in the database
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSessionKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT equivalent in JPA
    @Column(name = "ID")  // Maps to the 'ID' column
    private Long id;

    @Column(name = "SECRET_KEY", nullable = false, length = 600)  // Maps to the 'SECRET_KEY' column
    private String secretKey;

}