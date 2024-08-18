package com.sx.userserver.userTable.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sx.userserver.common.result.Result;
import com.sx.userserver.userTable.dto.AddUser;
import com.sx.userserver.userTable.dto.QueryPageUser;
import com.sx.userserver.userTable.dto.UpdateUser;
import com.sx.userserver.userTable.entity.UserTable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beetles
 * @since 2024-07-24
 */
public interface UserTableService extends IService<UserTable> {

    public Page<UserTable> getPageUser(QueryPageUser queryPageUser);
    public Result<?> deleteUserTable(Integer userId);
    public Result<?> updateUserTable(UpdateUser updateUser);
    public Result<?> addUserTable(AddUser addUser);
}
