package com.lv.product.service.impl;

import com.lv.product.dataobject.ProductInfo;
import com.lv.product.enums.ProductStatusEnum;
import com.lv.product.repository.ProductInfoRepository;
import com.lv.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product
 * @Date: 2019/1/24 12:02
 * @Author: Mr.Deng
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
