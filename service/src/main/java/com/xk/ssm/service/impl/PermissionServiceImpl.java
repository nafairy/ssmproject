package com.xk.ssm.service.impl;

import com.xk.ssm.dao.IPermissionDao;
import com.xk.ssm.domain.Permission;
import com.xk.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public void deleteById(String id) throws Exception {
        permissionDao.deleteFromRole_PermissionById(id);
        permissionDao.deleteFromPermissionById(id);
    }
}
