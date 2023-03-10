package com.foodhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foodhub.entity.Employee;
import com.foodhub.mapper.EmployeeMapper;
import com.foodhub.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
