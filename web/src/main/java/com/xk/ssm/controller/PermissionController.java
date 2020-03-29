package com.xk.ssm.controller;

import com.xk.ssm.domain.Permission;
import com.xk.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public String findAll(Model model) throws Exception {
        List<Permission> all = permissionService.findAll();
        model.addAttribute("permissionList",all);
        return "permission-list";
    }

    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(String id)throws Exception{
        permissionService.deleteById(id);
        return "redirect:/permission/findAll.do";
    }
}
