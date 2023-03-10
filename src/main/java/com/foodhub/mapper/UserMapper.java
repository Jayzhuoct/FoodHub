package com.foodhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.foodhub.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
