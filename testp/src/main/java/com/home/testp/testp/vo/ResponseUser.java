package com.home.testp.testp.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 로 넘어온 객체는 무시하겠다.
public class ResponseUser {

    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> orders;

}
