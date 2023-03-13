package com.foodhub.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foodhub.common.R;
import com.foodhub.entity.Category;
import com.foodhub.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        log.info("新增分类成功：" + category.toString());
        return R.success("添加成功！");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrap = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        queryWrap.orderByAsc(Category::getSort);
        //分页查询
        categoryService.page(pageInfo,queryWrap);
        log.info("查询分类信息成功{}"+pageInfo);
        return R.success(pageInfo);
    }

    /**
     * 根据id删除分类
     * 在service层重写删除方法，
     * 判断分类下关联没有菜品，如果有则提示不能删除
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        categoryService.remove(id);
        log.info("删除分类成功,id{}",id);
        return R.success("分类信息删除成功！");
    }

    /**
     * 根据id修改信息
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("修改信息：{}",category);
        categoryService.updateById(category);
        return R.success("修改信息成功！");
    }

}
