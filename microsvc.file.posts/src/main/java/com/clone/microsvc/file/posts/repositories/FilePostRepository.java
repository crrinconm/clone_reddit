package com.clone.microsvc.file.posts.repositories;

import com.clone.microsvc.file.posts.models.FilePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilePostRepository extends JpaRepository<FilePost, Long> {
}
