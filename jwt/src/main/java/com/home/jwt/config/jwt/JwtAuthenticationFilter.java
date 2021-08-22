package com.home.jwt.config.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.jwt.comm.model.User;
import com.home.jwt.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;



// 스프링시큐리티에서 UsernamePasswordAuthenticationfilter 가 있지만 이 필터를 커스텀 해야함
// login 요청해서 username password 전송하면
// 이 필터가 동작함 (원래)
// formLogin disaled 되어 작동 안하던 문제를 이 필터를 커스텀하여 수동으로 security 에 넣어줌

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        
        // username password 를 유저로부터 입력받음
        // PrincipalDetailsService 에서 loadUserByUsername() 함수 실행
        // PrincipalDetails 를 세션에 담음 (세션에 담지 않을경우 권한관리가 안됨)
        // jwt 토큰을 유저에게 응답으로 보내줌

        try{
            ObjectMapper map = new ObjectMapper();
            User user = map.readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

            // 여기서 loadUserByUsername 함수가 실행됨
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // authentication 객체가 session 영역에 저장됨 ==> 로그인이 되었다는 뜻
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            return authentication;
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return null;
    }

    // attemptAuthentication 실힝 후 인증이 정상적으로 되었으면 아래 함수가 실행됨.
    // JWT 토큰을 아래에서 생성하여 JWT 토큰을 응답해주면됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetailis.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))
                .withClaim("id", principalDetailis.getUser().getId())
                .withClaim("username", principalDetailis.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos"));
        response.addHeader("Authentication","Bearer "+jwtToken);

    }
}
