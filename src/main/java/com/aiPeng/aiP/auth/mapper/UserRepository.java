package com.aiPeng.aiP.auth.mapper;

import com.aiPeng.aiP.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author taojirun
 * @description
 * @create 2025/3/3 9:20
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
