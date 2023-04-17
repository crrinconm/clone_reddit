package com.clone.microsvc.file.posts.services;

import com.clone.microsvc.file.posts.dto.FilePostDTO;

import java.io.File;
import java.util.List;

public interface FilePostService {

    FilePostDTO create (FilePostDTO filePostDTO);

    FilePostDTO findById (String id);

    List<FilePostDTO> findAll ();

    FilePostDTO update (String id, FilePostDTO filePostDTO);

    void delete (String id);
}
