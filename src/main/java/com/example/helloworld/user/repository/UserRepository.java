package com.example.helloworld.user.repository;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.dto.FindAllUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("select userId, email, createdAt from users order by createdAt desc")
    List<FindAllUserDto> findAllUser();

    @Select("select * from users where userId = #{userId}")
    User findByUserId(String userId);
}
