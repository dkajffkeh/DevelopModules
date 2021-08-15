package com.basicmodule.user.service;

import com.basicmodule.user.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);
}
