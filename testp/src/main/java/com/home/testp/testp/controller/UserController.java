package com.home.testp.testp.controller;

import com.home.testp.testp.dto.UserDto;
import com.home.testp.testp.service.UserService;
import com.home.testp.testp.vo.RequestUser;
import com.home.testp.testp.vo.ResponseUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")  // "/" 는 안쓴것과 같은 의미
@AllArgsConstructor
public class UserController {

    private final Environment env;
    private final UserService userService;

    @PostMapping("/users")             //RequestUser -> 사용자가 입력한 값을 JSON 형태로 받아옴
    public ResponseEntity createUser(@RequestBody RequestUser user){

        ModelMapper modelMapper = new ModelMapper();

        //받아온 입력값을 Entity 로 보내야하기 때문에 user JSON 형태의 값을 UserDto 로 바꿈
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(user,UserDto.class);

        //user-> userDto 형태로 바꾼후 service 단으로 보내게됨.

        ResponseUser responseUser = modelMapper.map(userService.createUser(userDto),ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
    @GetMapping("/login")
    public ModelAndView goToLoginPage(ModelAndView mv){

        mv.setViewName("/home");

        return mv;
    }

}
