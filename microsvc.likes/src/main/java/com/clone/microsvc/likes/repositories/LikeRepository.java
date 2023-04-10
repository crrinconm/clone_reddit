package com.clone.microsvc.likes.repositories;

import com.clone.microsvc.likes.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
