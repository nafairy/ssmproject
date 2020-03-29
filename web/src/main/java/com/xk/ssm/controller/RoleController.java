package com.xk.ssm.controller;

import com.xk.ssm.domain.Permission;
import com.xk.ssm.domain.Role;
import com.xk.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public String findAll(Model model) throws Exception {
        List<Role> list=roleService.findAll();
        model.addAttribute("roleList",list);
        return "role-list";
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name="id") String roleId) throws Exception {
        roleService.deleteById(roleId);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findOtherPermission.do")
    public ModelAndView findOtherPermission(@RequestParam(name="id") String roleId)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList=roleService.findOtherPermission(roleId);
        Role role = roleService.findById(roleId);
        mv.addObject("permissionList",permissionList);
        mv.addObject("role",role);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole( String roleId,@RequestParam("ids") String[] permissionIds)throws Exception{
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:/role/findAll.do";
    }
}
