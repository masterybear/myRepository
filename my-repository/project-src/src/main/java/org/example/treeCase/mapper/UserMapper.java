package org.example.treeCase.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.example.treeCase.model.User;

public interface UserMapper {

    @Select("select * from users where email = #{email} and pwd = #{password}")
    @ResultMap("UserMap")
    User queryUser(@Param("email") String email,@Param("password") String password);

    @Select("select email from users where email = #{email}")
    @ResultMap("UserMap")
    User queryEmail(String email);

    @Select("select username from users where username = #{name}")
    @ResultMap("UserMap")
    User queryName(String name);

    @Select("select username from users where email = #{email}")
    String queryNameByEmail(String email);

    @Insert("insert into users values (null,#{username},#{email},#{password})")
    void addUser(User user);
}
