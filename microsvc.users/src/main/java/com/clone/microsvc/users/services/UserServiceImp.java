package com.clone.microsvc.users.services;

import com.clone.microsvc.users.dto.UserDTO;
import com.clone.microsvc.users.models.User;
import com.clone.microsvc.users.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO create(UserDTO userDTO) {
        User user= modelMapper.map(userDTO, User.class);
        User save= userRepository.save(user);
        return modelMapper.map(save,UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users= userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró por el id " + id));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        User user= userRepository.getReferenceById(id);
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhoto(userDTO.getPhoto());
        User save= userRepository.save(user);
        return modelMapper.map(save,UserDTO.class);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
