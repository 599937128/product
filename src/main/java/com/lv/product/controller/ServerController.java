package com.lv.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: product
 * @Date: 2019/1/25 10:22
 * @Author: Mr.lv
 * @Description:
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is product massage 2";
    }
}
