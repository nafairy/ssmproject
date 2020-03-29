package com.xk.ssm.service;

import com.xk.ssm.dao.IRoleDao;
import com.xk.ssm.domain.Permission;
import com.xk.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll()throws Exception;

    public void save(Role role);

    public void deleteById(String roleId)throws Exception;

    public List<Permission> findOtherPermission(String roleId)throws Exception;

    public Role findById(String roleId)throws Exception;

    public void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;
}
