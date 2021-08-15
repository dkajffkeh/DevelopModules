package com.home.testp.testp.service;

import com.home.testp.testp.dao.UserEntity;
import com.home.testp.testp.dao.UserRepository;
import com.home.testp.testp.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

  /*  @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.getUser(id);

        if(userEntity == null){
            throw new UsernameNotFoundException(id);
        }
        return new User(
                userEntity.getUserId()
                , userEntity.getEncryptedPwd()
                , true
                , true
                , true
                , true
                , new ArrayList<>()
        ); // SpringSecurity 에서 제공하는 객체로 비밀번호 의 유효성까지 체크가 완료 되었을시 User객체 반환
    }*/


    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }
}
