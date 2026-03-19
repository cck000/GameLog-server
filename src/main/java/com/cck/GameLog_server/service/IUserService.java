package com.cck.GameLog_server.service;

import com.cck.GameLog_server.dto.UserDTO;
import java.util.List;

public interface IUserService {

    UserDTO getUserById(Long id);

    
    List<UserDTO> getAllUsers();
    
  