package com.xk.ssm.service;

import com.xk.ssm.dao.IPermissionDao;
import com.xk.ssm.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll()throws Exception;

    public void save(Permission permission);

    public void deleteById(String id)throws Exception;
}
