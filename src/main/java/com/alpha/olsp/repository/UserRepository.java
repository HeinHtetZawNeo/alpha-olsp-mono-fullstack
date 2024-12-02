package com.alpha.olsp.repository;


import com.alpha.olsp.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}
