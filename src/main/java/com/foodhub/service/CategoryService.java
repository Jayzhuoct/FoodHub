package com.foodhub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.foodhub.entity.Category;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);

}
