package com.clone.microsvc.comments.services;

import com.clone.microsvc.comments.dto.CommentDTO;
import com.clone.microsvc.comments.models.Comment;
import com.clone.microsvc.comments.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDTO create(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        Comment saved = commentRepository.save(comment);
        return modelMapper.map(saved,CommentDTO.class);
    }

    @Override
    public CommentDTO findById(Long id) {
        Comment comment = commentRepository.findById(id)
                          .orElseThrow(() -> new EntityNotFoundException("No se encontró por el Id" + id));
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> findAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO update(Long id, CommentDTO commentDTO) {
        Comment comment= commentRepository.getReferenceById(id);
        comment.setComment(commentDTO.getComment());
        Comment save= commentRepository.save(comment);
        return modelMapper.map(save, CommentDTO.class);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
