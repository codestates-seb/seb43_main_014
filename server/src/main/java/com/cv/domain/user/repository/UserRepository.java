package com.cv.domain.user.repository;

import com.cv.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User.UserStatus findStatusByEmail(String email);
}
