package com.devox.app.security.repository;

import com.devox.app.model.security.Authority;
import com.devox.app.model.security.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Optional<Authority> findByName(AuthorityName roleName);


}
