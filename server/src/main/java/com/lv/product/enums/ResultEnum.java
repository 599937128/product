package com.lv.product.enums;

import lombok.Getter;

/**
 * @program: product
 * @Date: 2019/1/25 14:49
 * @Author: Mr.lv
 * @Description:
 */
@Getter
public enum  ResultEnum {
    PRODUCT_NOT_EXITS(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),
    ;

    private Integer code;

    private String massage;

    ResultEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
