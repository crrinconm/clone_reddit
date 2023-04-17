package com.clone.microsvc.posts.services;


import com.clone.microsvc.posts.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO create(PostDTO postDTO);
    List<PostDTO> findAll();
    PostDTO findById(String id);
    PostDTO update (String id, PostDTO postDTO);
    void delete (String id);
}
