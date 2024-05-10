package com.junhyeong.heroglass.repository;

import com.junhyeong.heroglass.domain.AppUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);

    AppUser findByUsername(String username);

    boolean existsByEmail(String email);

}
