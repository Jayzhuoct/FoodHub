package com.foodhub.dto;

import com.foodhub.entity.Setmeal;
import com.foodhub.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
