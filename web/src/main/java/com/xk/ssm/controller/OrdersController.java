package com.xk.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.xk.ssm.domain.Orders;
import com.xk.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv=new ModelAndView();
//        List<Orders> all = ordersService.findAll();
//        mv.addObject("ordersList",all);
//        mv.setViewName("orders-list");
//        return mv;
//    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required=true,defaultValue = "1")int page,@RequestParam(name="size",required =true,defaultValue = "1")int size)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Orders> all = ordersService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id",required = true)String ordersId) throws Exception {
        ModelAndView mv=new ModelAndView();
        Orders byId = ordersService.findById(ordersId);
        mv.addObject("orders",byId);
        mv.setViewName("orders-show");
        return mv;
    }


}
