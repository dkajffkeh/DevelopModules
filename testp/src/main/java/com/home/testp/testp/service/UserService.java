package com.home.testp.testp.service;

import com.home.testp.testp.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService  {

    UserDto createUser(UserDto userDto);
}
