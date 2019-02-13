package com.lv.product.service.impl;

import com.lv.product.dataobject.ProductInfo;
import com.lv.product.dto.CartDTO;
import com.lv.product.enums.ProductStatusEnum;
import com.lv.product.enums.ResultEnum;
import com.lv.product.exception.ProductException;
import com.lv.product.repository.ProductInfoRepository;
import com.lv.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @program: product
 * @Date: 2019/1/24 12:02
 * @Author: Mr.Deng
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
            // 判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXITS);
            }

            ProductInfo productInfo = productInfoOptional.get();
            // 判断库存是否足够
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            // 设置结果
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
