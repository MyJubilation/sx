package com.sx.userserver.foodTable.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sx.userserver.foodTable.dto.AddFood;
import com.sx.userserver.foodTable.dto.QueryPageFood;
import com.sx.userserver.foodTable.dto.UpdateFood;
import com.sx.userserver.foodTable.entity.FoodTable;

import org.springframework.web.multipart.MultipartFile;

public interface FoodTableService extends IService<FoodTable> {
    Page<FoodTable> getPageFood(QueryPageFood queryPageFood);

    String saveUserImg(MultipartFile file);

    boolean addFood(AddFood addFood);

    boolean updateFood(UpdateFood updateFood);
}
