package com.xapp.member.authentication.repository;


import com.xapp.member.authentication.entity.UserCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoriesRepository extends JpaRepository<UserCategories, String> {
    UserCategories findByCategoryName(String categoryName);
}
