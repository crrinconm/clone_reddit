package com.clone.microsvc.posts.services;


import com.clone.microsvc.posts.dto.PostDTO;
import com.clone.microsvc.posts.models.Post;

import java.util.List;

public interface PostService {

    PostDTO create(PostDTO postDTO);
    List<PostDTO> findAll();

    List<PostDTO> findBySubCategoryId(Long subCategoryId);
    PostDTO findById(String id);
    PostDTO update (String id, PostDTO postDTO);
    void delete (String id);
}
