package com.home.jwt.comm.repository;

import com.home.jwt.comm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD 함수를 JpaRepositrory 가 들고 있음
// @Repository 하는 어노테이션이 없어도 Ioc 됨
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy -> 문법
    // select * from user where username="?"
    User findByUsername(String username);

}
