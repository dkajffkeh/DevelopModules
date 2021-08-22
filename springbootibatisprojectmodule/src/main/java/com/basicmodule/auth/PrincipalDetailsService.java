package com.basicmodule.auth;


import com.basicmodule.common.model.User;
import com.basicmodule.common.repostitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Security 설정에서 loginprocessingUrl("/login") 설정이 되어있어
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는
// 함수가 실행됨
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // username 은 view 단에서 name="username" 으로 입력된 것이 이쪽으로 유입됨
    // username 외에 userId or userEmail 등으로 입력된경우 스프링 시큐리티 정상작동 X
    // 변경되었을 경우 SpringSecurity 에서 .usernameParameter 속성을 이용하여 변경 해야함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);

        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }

        return null;
    }
}
