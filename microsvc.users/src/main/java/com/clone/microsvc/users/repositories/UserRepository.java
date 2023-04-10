package com.clone.microsvc.users.repositories;

import com.clone.microsvc.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
