package com.clone.microsvc.likes.services;

import com.clone.microsvc.likes.dto.LikeDTO;

import java.util.List;

public interface LikeService {

    LikeDTO create (LikeDTO likeDTO);
    List<LikeDTO> findAll();
    LikeDTO findById (Long id);
    LikeDTO update (Long id, LikeDTO likeDTO);
    void delete (Long id);

}
