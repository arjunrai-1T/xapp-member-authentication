package com.xapp.member.authentication.repository;

import com.xapp.member.authentication.entity.UserStatusHashList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusHashListRepository extends JpaRepository<UserStatusHashList, Long> {
    UserStatusHashList findByUserStatusKey(String userStatusKey);
}
