package com.lv.product.exception;

import com.lv.product.enums.ResultEnum;

/**
 * @program: product
 * @Date: 2019/1/25 14:47
 * @Author: Mr.lv
 * @Description:
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMassage());
        this.code = code;
    }
}
