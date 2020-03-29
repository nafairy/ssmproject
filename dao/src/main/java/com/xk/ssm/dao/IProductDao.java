package com.xk.ssm.dao;

import com.xk.ssm.domain.product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IProductDao {

    @Select("select * from product")
    public List<product> findAll() throws Exception;

    @Insert("insert into product values(#{productNum},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(product pro);

    @Select("select * from product where id=#{id}")
    public product findById(String id)throws Exception;
}