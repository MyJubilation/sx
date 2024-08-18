package com.sx.userserver.userTable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sx.userserver.common.result.Result;
import com.sx.userserver.userTable.dto.AddUser;
import com.sx.userserver.userTable.dto.QueryPageUser;
import com.sx.userserver.userTable.dto.UpdateUser;
import com.sx.userserver.userTable.entity.UserTable;
import com.sx.userserver.userTable.mapper.UserTableMapper;
import com.sx.userserver.userTable.service.UserTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Beetles
 * @since 2024-07-24
 */
@Service
public class UserTableServiceImpl extends ServiceImpl<UserTableMapper, UserTable> implements UserTableService {

    @Autowired
    private UserTableMapper userTableMapper;

    @Override

    public Page<UserTable> getPageUser(QueryPageUser queryPageUser) {
        Page<UserTable> page = new Page<>(queryPageUser.getPageNumber(),queryPageUser.getPageSize());

        QueryWrapper<UserTable> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(queryPageUser.getUserName())){
            queryWrapper.like("user_name",queryPageUser.getUserName());
        }

        return this.page(page,queryWrapper);

    }
    public Result<?> deleteUserTable(Integer userId){

        QueryWrapper<UserTable> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id", userId);
        int delete = userTableMapper.delete(queryWrapper);
        if (delete>0) {
            return new Result<>().success("用户删除成功");
        } else {
            return new Result<>().error("用户删除失败");
        }
    }

    @Override
    public Result<?> updateUserTable(UpdateUser updateUser) {
        UserTable userTable = new UserTable();
        BeanUtils.copyProperties(updateUser,userTable);
        if(this.updateById(userTable)){
            return new Result<>().success("更新成功");
        }else{
            return new Result<>().error("更新失败");
        }
    }

    @Override
    public Result<?> addUserTable(AddUser addUser) {
        UserTable user = new UserTable();
        BeanUtils.copyProperties(addUser,user);
        if(this.save(user)){
            return new Result<>().success("添加成功");
        }else {
            return new Result<>().error("添加失败");
        }
    }
}
