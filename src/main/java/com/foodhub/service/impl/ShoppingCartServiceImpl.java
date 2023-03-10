package com.foodhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foodhub.entity.ShoppingCart;
import com.foodhub.mapper.ShoppingCartMapper;
import com.foodhub.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
