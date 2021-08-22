package com.basicmodule.common.repostitory;

import com.basicmodule.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
//CRUD 함수를 JpaRepositrory 가 들고 있음
// @Repository 하는 어노테이션이 없어도 Ioc 됨
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy -> 문법
    // select * from user where username="?"
    User findByUsername(String username);

}
