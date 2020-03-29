package com.xk.ssm.dao;

import com.xk.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id)throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll()throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);

    @Delete("delete from role_permission where permissionId=#{id}")
    public void deleteFromRole_PermissionById(String id);

    @Delete("delete from permission where id=#{id}")
    public void deleteFromPermissionById(String id);
}
