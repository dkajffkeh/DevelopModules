package com.home.testp.testp.dao;

import lombok.Data;

@Data
public class UserEntity {

    private Long id;

    private String email;
    private String name;
    private String userId;
    private String encryptedPwd;



}
