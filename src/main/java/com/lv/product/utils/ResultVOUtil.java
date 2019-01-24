package com.lv.product.utils;

import com.lv.product.VO.ResultVO;

/**
 * @program: product
 * @Date: 2019/1/24 15:22
 * @Author: Mr.lv
 * @Description:
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
