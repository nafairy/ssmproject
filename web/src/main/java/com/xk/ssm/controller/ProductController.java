package com.xk.ssm.controller;

import com.xk.ssm.domain.product;
import com.xk.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    //查询所有产品信息
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<product> all = productService.findAll();
        mv.addObject("productList",all);
        mv.setViewName("product-list");
        return mv;
    }

    //保存产品
    @RequestMapping("/save.do")
    public String save(product pro) {
        productService.save(pro);
        return "redirect:findAll.do";
    }


}
