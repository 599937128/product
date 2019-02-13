package com.lv.product.service;

import com.lv.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @program: product
 * @Date: 2019/1/24 14:22
 * @Author: Mr.lv
 * @Description:
 */
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypelist);
}
