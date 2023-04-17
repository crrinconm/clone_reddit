package com.clone.microsvc.posts.repositories;

import com.clone.microsvc.posts.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
