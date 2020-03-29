package com.xk.ssm.dao;

import com.xk.ssm.domain.Role;
import com.xk.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many =
            @Many(select = "com.xk.ssm.dao.IRoleDao.findByUserId")) })
    public UserInfo findByName(String username)throws Exception;

    @Select("select * from users")
    public List<UserInfo> findAll()throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status)values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.xk.ssm.dao.IRoleDao.findByUserId"))
    })
    public UserInfo findById(String id) throws Exception;

    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    public List<Role> findOtherRole(String id)throws Exception;

    @Insert("insert into users_role values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId)throws Exception;
}
