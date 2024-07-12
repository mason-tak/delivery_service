package com.delivery.service.db.user;

import com.delivery.service.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 특정 사용자 찾기
    // select * from user where id = ? and status = ? order by id desc limit 1;
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, UserStatus userStatus);

    // 로그인 할때
    // select * from user where email = ? and password = ? and status = ? order by id desc limit 1;
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus userStatus);
}
