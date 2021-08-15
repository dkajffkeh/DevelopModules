package com.home.testp.testp.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {

    private static ArrayList<UserEntity> userList = new ArrayList<>();



    public UserEntity getUser(String id){


        return null;
    }

    public void save(UserEntity userEntity) {

        userList.add(userEntity); //추가 진행

    }
}
