package com.clone.microsvc.likes.services;

import com.clone.microsvc.likes.dto.LikeDTO;
import com.clone.microsvc.likes.models.Like;
import com.clone.microsvc.likes.repositories.LikeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImp implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LikeDTO create(LikeDTO likeDTO) {
       Like like= modelMapper.map(likeDTO, Like.class);
       Like save= likeRepository.save(like);
        return modelMapper.map(save, LikeDTO.class);
    }

    @Override
    public List<LikeDTO> findAll() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(like -> modelMapper.map(like, LikeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LikeDTO findById(Long id) {
        Like like= likeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró por el id " + id));
        return modelMapper.map(like, LikeDTO.class);
    }

    @Override
    public LikeDTO update(Long id, LikeDTO likeDTO) {
        Like like= likeRepository.getReferenceById(id);
        like.setLike(likeDTO.getLike());
        Like save= likeRepository.save(like);
        return modelMapper.map(save, LikeDTO.class);
    }

    @Override
    public void delete(Long id) {
        likeRepository.deleteById(id);

    }
}
