package com.foodhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foodhub.entity.OrderDetail;
import com.foodhub.mapper.OrderDetailMapper;
import com.foodhub.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}