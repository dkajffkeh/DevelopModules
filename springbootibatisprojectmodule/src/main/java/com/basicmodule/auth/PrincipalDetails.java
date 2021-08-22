package com.basicmodule.auth;

import com.basicmodule.common.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// Security Session 에 들어갈수 있는 타입은 => Authentication 타입이여야 하며
// Authentication 타입은 UserDetails 타입을 갖고 있어야함
// 시큐리티가 /login 주소 요청을 낚아 채서 로그인을 진행함
// 로그인이 완료될 경우 SpringSecurity 에서는 자체적으로 session 을 만들어 저장함//
// key 값은 Security ContextHolder
// Object -> Authentication 타입 객체
// Authentication 안에 User 정보가 있어야함
// User 오브젝트 타입 => UserDetails 타입 객체 여야함.

@NoArgsConstructor
@Data
@ToString

// SpringSecurity 는 Authentication 타입을 세션으로 저장하지만(UserDetails 를 상속받은 객체)
// 구글로그인 같은경우에는 OAuth2User 를 상속받아 Authentication 을 만들게됨
// 그럴경우 UserDetails 와 Oauth2User 를 상속받은 객체가 서로 분리되어
// 한곳에 통합시킬 필요가 있음 하여 PrincipalDetails 가 두가지를 전부 상속받아
// 하나의 Authentication 객체를 형성하게 만든후 Security 에 던져 로그인을 완료함.
public class PrincipalDetails implements UserDetails , OAuth2User {

    @Autowired
    private User user;

    private Map<String,Object> attributes;

    // 일반 로그인시 사용되는 객체
    public PrincipalDetails(User user) {
        this.user=user;
    }

    // 구글 로그인시 사용되는 객체
    public PrincipalDetails(User user, Map<String,Object> attributes){
        this.user = user;
        this.attributes = attributes;
    }

    // 해당 유저의 권한을 리턴하는 객체
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();

        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // cred 암호화 키가 만료되었는가
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
