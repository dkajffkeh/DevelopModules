package com.basicmodule.user.service;

import com.basicmodule.user.dao.UserEntity;
import com.basicmodule.user.dao.UserRepository;
import com.basicmodule.user.dto.UserDto;
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
public class UserServiceimpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.getUser(email);

        if(userEntity == null){
            throw new UsernameNotFoundException(email);
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
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        userDto.setUserId(UUID.randomUUID().toString());

        // 객체에 대한 주입역할을 해주는 메소드
        ModelMapper mapper = new ModelMapper();

        //딱 맞아 떨어지지 않으면 객체에 대한 주입을 하지 않겠다는 선언.
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // userDto 에 담긴 상태값을 UserEntity 값으로 옮겨담게됨.
        UserEntity userEntity = mapper.map(userDto,UserEntity.class);

        //기능구현이 안돼있어 일단 하드코딩
        userEntity.setEncryptedPwd(bCryptPasswordEncoder.encode(userDto.getPwd()));

        userRepository.save(userEntity);

        return mapper.map(userEntity,UserDto.class);
    }


}
