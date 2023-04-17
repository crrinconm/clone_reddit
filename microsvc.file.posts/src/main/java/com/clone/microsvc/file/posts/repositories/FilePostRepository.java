package com.clone.microsvc.file.posts.repositories;

import com.clone.microsvc.file.posts.models.FilePost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilePostRepository extends MongoRepository<FilePost, String> {
}
