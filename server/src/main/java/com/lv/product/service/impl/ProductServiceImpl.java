package com.lv.product.service.impl;

import com.lv.product.common.ProductInfoOutput;
import com.lv.product.dataobject.ProductInfo;
import com.lv.product.dto.CartDTO;
import com.lv.product.enums.ProductStatusEnum;
import com.lv.product.enums.ResultEnum;
import com.lv.product.exception.ProductException;
import com.lv.product.repository.ProductInfoRepository;
import com.lv.product.service.ProductService;
import com.lv.product.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: product
 * @Date: 2019/1/24 12:02
 * @Author: Mr.Deng
 * @Description:
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreseStock(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfoList = decreseStockProcess(cartDTOList);
        // 发送mq消息
        List<ProductInfoOutput> productInfoOutputs = productInfoList.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputs));
    }

    @Transactional
    public List<ProductInfo> decreseStockProcess(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
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
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
