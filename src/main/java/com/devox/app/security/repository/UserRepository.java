package com.devox.app.security.repository;

import com.devox.app.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by stephan on 20.03.16.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Override
    <S extends User> S save(S entity);
}
