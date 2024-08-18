package com.sx.userserver.foodTable.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sx.userserver.foodTable.dto.AddFood;
import com.sx.userserver.foodTable.dto.QueryPageFood;
import com.sx.userserver.foodTable.dto.UpdateFood;
import com.sx.userserver.foodTable.entity.FoodTable;
import com.sx.userserver.foodTable.mapper.FoodTableMapper;
import com.sx.userserver.foodTable.service.FoodTableService;
import com.sx.userserver.foodTable.entity.FoodTable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FoodTableServiceImpl extends ServiceImpl<FoodTableMapper,FoodTable> implements FoodTableService {



    @Override
    public Page<FoodTable> getPageFood(QueryPageFood queryPageFood) {

        Page<FoodTable> p = new Page<>(queryPageFood.getPageNumber(), queryPageFood.getPageSize());

        QueryWrapper<FoodTable> w = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(queryPageFood.getFoodName())){
            w.like("food_name", queryPageFood.getFoodName());
        }

        return this.page(p, w);
    }

    @Override
    public String saveUserImg(MultipartFile file) {
//        todo
        String descDir = "E:\\img";
        String fileName = file.getOriginalFilename();
        String filePath = descDir + File.separator + fileName;
        File descFile = new File(filePath);

        try {
            file.transferTo(descFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //todo
        String imgUrl = "http://localhost:8084/view/" + fileName;
        return imgUrl;
    }

    @Override
    public boolean addFood(AddFood addFood) {
        FoodTable foodTable = new FoodTable();

        BeanUtils.copyProperties(addFood, foodTable);

        return this.save(foodTable);
    }

    @Override
    public boolean updateFood(UpdateFood updateFood) {
        FoodTable foodTable = new FoodTable();
        BeanUtils.copyProperties(updateFood, foodTable);
        return this.updateById(foodTable);
    }
}