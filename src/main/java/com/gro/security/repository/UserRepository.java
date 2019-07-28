package com.gro.security.repository;

import com.gro.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Metao.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
