package com.clone.microsvc.comments.services;

import com.clone.microsvc.comments.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    // Defininimos el CRUD

    CommentDTO create (CommentDTO commentDTO);

    CommentDTO findById (Long id);

    List<CommentDTO> findAll ();

    CommentDTO update (Long ig, CommentDTO commentDTO);

    void delete (Long id);



}
