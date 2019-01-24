package com.lv.product.service;

import com.lv.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @program: product
 * @Date: 2019/1/24 12:00
 * @Author: Mr.lv
 * @Description:
 */
public interface ProductService {

    /**
     *  查询商品列表 查询所有的在架的商品
     */
    List<ProductInfo> findUpAll();
}
