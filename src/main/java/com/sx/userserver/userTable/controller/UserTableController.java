package com.sx.userserver.userTable.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sx.userserver.common.result.Result;
import com.sx.userserver.userTable.dto.AddUser;
import com.sx.userserver.userTable.dto.QueryPageUser;
import com.sx.userserver.userTable.dto.UpdateUser;
import com.sx.userserver.userTable.entity.UserTable;
import com.sx.userserver.userTable.service.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Beetles
 * @since 2024-07-24
 */
@RestController
@RequestMapping("/userTable")
public class UserTableController {

    @Autowired
    private UserTableService userTableService;


    @GetMapping("/page")
    public Result<?> page(QueryPageUser queryPageUser){
        Page<UserTable> pageUser = userTableService.getPageUser(queryPageUser);
        JSONObject obj = new JSONObject();
        obj.put("total", pageUser.getTotal());
        obj.put("rows", pageUser.getRecords());

        return new Result<>().success().put(obj);

    }
    @GetMapping("/delete")
    public Result<?> deleteUserTable(Integer userId){

        return userTableService.deleteUserTable(userId);
    }
    @PostMapping("/update")
    public Result<?> updateUserTable(@RequestBody UpdateUser updateUser){
        return userTableService.updateUserTable(updateUser);
    }
    @PostMapping("/add")
    public Result<?> addUserTable(@RequestBody AddUser addUser){
        return userTableService.addUserTable(addUser);
    }
}
