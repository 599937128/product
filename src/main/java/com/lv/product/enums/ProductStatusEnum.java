package com.lv.product.enums;

import lombok.Getter;

/**
 * @program: product
 * @Date: 2019/1/24 14:13
 * @Author: Mr.lv
 * @Description: 商品上下架状态
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1,"下架"),
    ;
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
