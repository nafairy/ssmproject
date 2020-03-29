package com.xk.ssm.service.impl;

import com.xk.ssm.dao.IUserDao;
import com.xk.ssm.domain.Role;
import com.xk.ssm.domain.UserInfo;
import com.xk.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);
        User user = new User(userInfo.getUsername(),  userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true, true, true, true, authoritys);
        return user;
    }
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

//    public List<SimpleGrantedAuthority> getAuthority(){
//        List<SimpleGrantedAuthority> list=new ArrayList<>();
//        list.add(new SimpleGrantedAuthority("ROLE_USER"));
//        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return list;
//    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRole(String id) throws Exception {
        return userDao.findOtherRole(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for(String roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
