package com.clone.microsvc.posts.services;


import com.clone.microsvc.posts.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO create(PostDTO postDTO);
    List<PostDTO> findAll();
    PostDTO findById(Long id);
    PostDTO update (Long ig, PostDTO postDTO);
    void delete (Long id);
}
