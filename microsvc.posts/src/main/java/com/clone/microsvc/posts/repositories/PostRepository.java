package com.clone.microsvc.posts.repositories;

import com.clone.microsvc.posts.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
