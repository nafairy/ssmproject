package com.xk.ssm.dao;


import com.xk.ssm.domain.Permission;
import com.xk.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId=#{userId} )")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.xk.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findByUserId(String userId) throws Exception;


    @Select("select * from role")
    public List<Role> findAll()throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role);

    @Delete("delete from users_role where roleId=#{roleId}")
    public void deleteFromUser_RoleByRoleId(String roleId)throws Exception;

    @Delete("delete from role_permission where roleId=#{roleId}")
    public void deleteFromRole_PermissionByRoleId(String roleId)throws Exception;

    @Delete("delete from role where id=#{roleId}")
    public void deleteById(String roleId)throws Exception;

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findOtherPermission(String roleId)throws Exception;

    @Select("select * from role where id=#{roleId}")
    public Role findById(String roleId)throws Exception;

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    public void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId)throws Exception;
}
