package com.lv.product.client;

import com.lv.product.common.DecreaseStockInput;
import com.lv.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @program: order
 * @Date: 2019/1/25 11:50
 * @Author: Mr.lv
 * @Description:
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> prodcutIdList);

    @PostMapping("/product/decreseStock")
    public void decreseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
