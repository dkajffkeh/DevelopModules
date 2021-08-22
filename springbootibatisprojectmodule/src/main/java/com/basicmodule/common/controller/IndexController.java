package com.basicmodule.common.controller;

import com.basicmodule.auth.PrincipalDetails;
import com.basicmodule.common.model.User;
import com.basicmodule.common.repostitory.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"","/"})
    public String index(){

        return "home";
    }

    //Oauth2 login 으로도 PrincipalDetails 로 받고
    //일반로그인도 같은 타입으로 받음
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info(principalDetails.getUser().toString());
       return "user";
    }

    @GetMapping("/loginForm")
    public String loginForm(){

        return "mustache/view/login/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){

        return "mustache/view/login/joinForm";
    }

    @PostMapping("/join")
    public String join(User user){

        // password encode
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);


        return "redirect:/ ";
    }

    @GetMapping("/joinProc")
    public @ResponseBody
    String joinProc(){

        return "회원가입 완료!";
    }

    @GetMapping("/admin")
    public @ResponseBody
    String admin(){

        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody
    String manager(){

        return "manager";
    }

    /*@Secured("ROLE_ADMIN")*/
    // 메서드가 실행되기 직전에 실행됨. 여러개의 권한을 넣을때 사용됨
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @GetMapping("/data")
    public @ResponseBody String data(){

        return "data";
    }

    @GetMapping("/test/login")
    public @ResponseBody String loginTest(@AuthenticationPrincipal  Authentication authentication){



        return "세션정보 확인하기";
    }






}
