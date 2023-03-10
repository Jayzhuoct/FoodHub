package com.foodhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foodhub.entity.DishFlavor;
import com.foodhub.mapper.DishFlavorMapper;
import com.foodhub.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
