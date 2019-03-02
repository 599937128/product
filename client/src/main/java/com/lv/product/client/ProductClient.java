package com.lv.product.client;

import com.lv.product.common.DecreaseStockInput;
import com.lv.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
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
@FeignClient(name = "product",
        fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> prodcutIdList);

    @PostMapping("/product/decreseStock")
    public void decreseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @Component
    static class ProductClientFallback implements ProductClient {

        @Override
        public String productMsg() {
            return null;
        }

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> prodcutIdList) {
            return null;
        }

        @Override
        public void decreseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
    }
}
