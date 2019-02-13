package com.lv.product.common;

import lombok.Data;

/**
 * @program: product
 * @Date: 2019/1/25 17:56
 * @Author: Mr.lv
 * @Description:
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
