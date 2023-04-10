package com.clone.microsvc.users.services;

import com.clone.microsvc.users.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create (UserDTO userDTO);
    List<UserDTO> findAll();
    UserDTO findById (Long id);
    UserDTO update (Long id, UserDTO userDTO);
    void delete (Long id);
}
