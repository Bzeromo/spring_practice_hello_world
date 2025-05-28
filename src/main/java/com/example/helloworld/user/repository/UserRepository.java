package com.example.helloworld.user.repository;

import com.example.helloworld.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("select userId, email, createdAt from users order by createdAt desc")
    List<User> findAllUser();

    @Select("select * from users where userId = #{userId}")
    List<User> findByUserId(String userId);
}
