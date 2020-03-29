package com.xk.ssm.service;

import com.xk.ssm.domain.Role;
import com.xk.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<UserInfo> findAll() throws Exception;

    public void save(UserInfo userInfo);

    public UserInfo findById(String id) throws Exception;

    public List<Role> findOtherRole(String id)throws Exception;

    public void addRoleToUser(String userId, String[] roleIds)throws Exception;
}
