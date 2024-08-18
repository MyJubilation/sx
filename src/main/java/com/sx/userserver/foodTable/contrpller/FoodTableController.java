package com.sx.userserver.foodTable.contrpller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sx.userserver.common.result.Result;
import com.sx.userserver.foodTable.dto.AddFood;
import com.sx.userserver.foodTable.dto.QueryPageFood;
import com.sx.userserver.foodTable.dto.UpdateFood;
import com.sx.userserver.foodTable.entity.FoodTable;
import com.sx.userserver.foodTable.service.FoodTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/food")
public class FoodTableController {
    @Autowired
    private FoodTableService foodTableService;

    @GetMapping("/page")
    public Result<?> page(QueryPageFood queryPageFood){
        Page<FoodTable> pageFood = foodTableService.getPageFood(queryPageFood);
        JSONObject obj = new JSONObject();
        obj.put("total", pageFood.getTotal());
        obj.put("rows", pageFood.getRecords());

        return new Result<>().success().put(obj);
    }

    @PostMapping("/img")
    public Result<?> saveFoodImg(MultipartFile file){
        String imgUrl = foodTableService.saveUserImg(file);
        return new Result<>().success().put(imgUrl);
    }

    @PostMapping("/add")
    public Result<?> addFood(@RequestBody AddFood addFood){
        boolean result = foodTableService.addFood(addFood);
        if(result){
            return new Result<>().success();
        }else {
            return new Result<>().error();
        }
    }

    @PostMapping("/update")
    public Result<?> updateFood(@RequestBody UpdateFood updateFood){
        boolean result = foodTableService.updateFood(updateFood);
        if(result){
            return new Result<>().success();
        }else {
            return new Result<>().error();
        }
    }

    @GetMapping("/delete")
    public Result<?> deleteUser(Integer id){
        boolean result = foodTableService.removeById(id);
        if(result){
            return new Result<>().success();
        }else {
            return new Result<>().error();
        }
    }
}
