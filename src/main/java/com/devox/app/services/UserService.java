package com.devox.app.services;

import com.devox.app.model.security.User;

public interface UserService {

    Boolean existsByUsername(String username);

    <S extends User> S save(S entity);


}
