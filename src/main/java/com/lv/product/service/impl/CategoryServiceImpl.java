package com.lv.product.service.impl;

import com.lv.product.dataobject.ProductCategory;
import com.lv.product.repository.ProductCategoryRepository;
import com.lv.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product
 * @Date: 2019/1/24 14:25
 * @Author: Mr.Deng
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypelist) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypelist);
    }
}
