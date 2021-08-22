package com.basicmodule.auth.outh;

import com.basicmodule.auth.PrincipalDetails;
import com.basicmodule.auth.outh.provider.FacebookUserInfo;
import com.basicmodule.auth.outh.provider.GoogleUserInfo;
import com.basicmodule.auth.outh.provider.NaverUserInfo;
import com.basicmodule.auth.outh.provider.OAuth2UserInfo;
import com.basicmodule.common.model.User;
import com.basicmodule.common.repostitory.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    // ** 해당함수 실행 종료시 AuthenticationPrincipal 어노테이션이 만들어진다.
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 구글로부터 회원 프로필을 받아준다
        OAuth2User oauth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
        }

        if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
        }

        if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
        }

        String provider = oAuth2UserInfo.getProvider(); // google or facebook or else
        String providerId = oAuth2UserInfo.getProviderId(); // sub:123819023859345 or else
        String username = provider+"_"+providerId; // google_123890348530945
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";

        // 회원가입 진행
        User userEntity = userRepository.findByUsername(username);
        
        // 회원 없음
        if(userEntity == null){
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }
        return new PrincipalDetails(userEntity,oauth2User.getAttributes()); // Oauth2 User type 리턴함.
    }
}
