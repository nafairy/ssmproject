package com.xk.ssm.controller;

import com.xk.ssm.domain.Role;
import com.xk.ssm.domain.UserInfo;
import com.xk.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public String findAll(Model model) throws Exception {
        List<UserInfo> all = userService.findAll();
        model.addAttribute("userList",all);
        return "user-list";
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/findById.do")
    public String findById(Model model,@RequestParam(value = "id") String id) throws Exception {
        UserInfo user = userService.findById(id);
        model.addAttribute("user",user);
        return "user-show";
    }

    @RequestMapping("/findUserAndAllRoleById.do")
    public ModelAndView findUserAndAllRoleById(String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        List<Role> otherRole=userService.findOtherRole(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRole);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam("userId") String userId,@RequestParam("ids") String[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:/user/findAll.do";
    }
}
