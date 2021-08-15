package com.basicmodule.user.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {

    private static ArrayList<UserEntity> userList = new ArrayList<>();

    public UserEntity getUser(String email){

        UserEntity returnUserEntity = new UserEntity();

        for(UserEntity userEntity : userList){

            if(userEntity.getEmail().equals(email)){
                returnUserEntity = userEntity;
            }

        }
        return returnUserEntity;
    }

    public void save(UserEntity userEntity) {

        userList.add(userEntity); //추가 진행

    }
}
