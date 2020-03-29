package com.xk.ssm.service.impl;

import com.xk.ssm.dao.IProductDao;
import com.xk.ssm.domain.product;
import com.xk.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public void save(product pro) {
        productDao.save(pro);
    }
}
