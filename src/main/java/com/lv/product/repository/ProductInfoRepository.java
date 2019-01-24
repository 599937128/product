package com.lv.product.repository;

import com.lv.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: product
 * @Date: 2019/1/24 11:31
 * @Author: Mr.lv
 * @Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
