package com.xk.ssm.service;

import com.xk.ssm.domain.product;

import java.util.List;

public interface IProductService {

    public List<product> findAll() throws Exception;

    public void save(product pro);
}
