package com.clone.microsvc.posts.services;

import com.clone.microsvc.posts.dto.PostDTO;
import com.clone.microsvc.posts.models.Post;
import com.clone.microsvc.posts.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PostDTO create(PostDTO postDTO) {
        Post post= modelMapper.map(postDTO, Post.class);
        Post save= postRepository.save(post);
        return modelMapper.map(save, PostDTO.class);
    }

    @Override
    public List<PostDTO> findAll() {
        List<Post> posts= postRepository.findAll();
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findBySubCategoryId(Long subCategoryId) {
        List<Post> posts= postRepository.findBySubCategoryId(subCategoryId);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PostDTO findById(String id) {
        Post post= postRepository.findById(id)
                .orElseThrow(null);
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public PostDTO update(String id, PostDTO postDTO) {
        Post post= postRepository.findById(id).orElseThrow(null);
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        Post save= postRepository.save(post);
        return modelMapper.map(save, PostDTO.class);
    }

    @Override
    public void delete(String id) {
        postRepository.deleteById(id);

    }
}
