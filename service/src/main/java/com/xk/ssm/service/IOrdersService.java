package com.xk.ssm.service;


import com.xk.ssm.domain.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface IOrdersService {

    public List<Orders> findAll(int page,int size) throws Exception;

    public Orders findById(String ordersId)throws Exception;
}
