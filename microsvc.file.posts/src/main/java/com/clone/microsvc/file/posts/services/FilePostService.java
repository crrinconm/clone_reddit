package com.clone.microsvc.file.posts.services;

import com.clone.microsvc.file.posts.dto.FilePostDTO;

import java.io.File;
import java.util.List;

public interface FilePostService {

    FilePostDTO create (FilePostDTO filePostDTO);

    FilePostDTO findById (Long id);

    List<FilePostDTO> findAll ();

    FilePostDTO update (Long id, FilePostDTO filePostDTO);

    void delete (Long id);
}
