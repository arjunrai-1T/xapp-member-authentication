package com.xapp.member.authentication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER_CATEGORIES")
@Getter
@Setter
public class UserCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID", nullable = false, updatable = false)
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", length = 400, nullable = false, unique = true)
    private String categoryName;

    @Column(name = "PARENT_CATEGORY_NAME", length = 400)
    private String parentCategoryName;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean isDeleted;

    @Column(name = "CREATION_DATETIME", nullable = false)
    private LocalDateTime creationDatetime = LocalDateTime.now();

    // Self-referencing relationship for parent category
    @ManyToOne
    @JoinColumn(name = "PARENT_CATEGORY_NAME", referencedColumnName = "CATEGORY_NAME", insertable = false, updatable = false)
    private UserCategories parentCategory;

}