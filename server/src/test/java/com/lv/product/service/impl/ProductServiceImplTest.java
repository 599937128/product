package com.lv.product.service.impl;

import com.lv.product.dataobject.ProductInfo;
import com.lv.product.dto.CartDTO;
import com.lv.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @program: product
 * @Date: 2019/1/24 14:17
 * @Author: Mr.lv
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertTrue(productInfos.size() > 0);
    }

    @Test
    public void findList() throws Exception {
        List<ProductInfo> result = productService.findList(Arrays.asList("157875196366160022", "157875227953464068"));
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void decreseStock() throws Exception {
        CartDTO cartDTO = new CartDTO("157875196366160022", 2);
        productService.decreseStock(Arrays.asList(cartDTO));
    }
}