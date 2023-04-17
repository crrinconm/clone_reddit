package com.clone.microsvc.file.posts.services;

import com.clone.microsvc.file.posts.dto.FilePostDTO;
import com.clone.microsvc.file.posts.models.FilePost;
import com.clone.microsvc.file.posts.repositories.FilePostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilePostImp implements FilePostService{

    @Autowired
    private FilePostRepository filePostRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FilePostDTO create(FilePostDTO filePostDTO) {
        FilePost filePost= modelMapper.map(filePostDTO,FilePost.class);
        FilePost save= filePostRepository.save(filePost);
        return modelMapper.map(save, FilePostDTO.class);
    }

    @Override
    public FilePostDTO findById(String id) {
        FilePost filePost= filePostRepository.findById(id)
                .orElseThrow(null);
        return modelMapper.map(filePost, FilePostDTO.class);
    }

    @Override
    public List<FilePostDTO> findAll() {
        List<FilePost> filePost= filePostRepository.findAll();
        return filePost.stream().map(filePost1 -> modelMapper.map(filePost1, FilePostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public FilePostDTO update(String id, FilePostDTO filePostDTO) {
        FilePost filePost= filePostRepository.findById(id).orElseThrow(null);
        filePost.setFile(filePostDTO.getFile());
        FilePost save= filePostRepository.save(filePost);
        return modelMapper.map(save, FilePostDTO.class);
    }

    @Override
    public void delete(String id) {
        filePostRepository.deleteById(id);
    }
}
