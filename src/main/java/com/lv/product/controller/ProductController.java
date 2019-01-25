package com.lv.product.controller;

import com.lv.product.VO.ProductInfoVO;
import com.lv.product.VO.ProductVO;
import com.lv.product.VO.ResultVO;
import com.lv.product.dataobject.ProductCategory;
import com.lv.product.dataobject.ProductInfo;
import com.lv.product.dto.CartDTO;
import com.lv.product.service.CategoryService;
import com.lv.product.service.ProductService;
import com.lv.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: product
 * @Date: 2019/1/24 10:57
 * @Author: Mr.lv
 * @Description: 商品
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 1.查询所有在架的商品
     * 2.获取类目的type列表
     * 3.查询类目
     * 4.构造数据
     */

    @GetMapping("/list")
    private ResultVO<ProductVO> list() {
        // 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //获取所有在架商品的type列表
        List<Integer> categoryTypeList = productInfoList.stream().
                map(ProductInfo::getCategoryType).
                collect(Collectors.toList());
        // 从数据库中查询类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    // 内容拷贝
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
       return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表(给订单服务使用)
     * @param prodcutIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> prodcutIdList) {
        return productService.findList(prodcutIdList);
    }

    @PostMapping("/decreseStock")
    public void decreseStock(@RequestBody List<CartDTO> cartDTOList) {
        productService.decreseStock(cartDTOList);
    }
}
