package com.lv.product.repository;

import com.lv.product.dataobject.ProductCategory;
import com.lv.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: product
 * @Date: 2019/1/24 11:31
 * @Author: Mr.lv
 * @Description: 查找类目
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypelist);
}
