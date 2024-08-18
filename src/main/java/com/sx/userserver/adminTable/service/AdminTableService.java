package com.sx.userserver.adminTable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sx.userserver.adminTable.dto.LoginUser;
import com.sx.userserver.adminTable.entity.AdminTable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beetles
 * @since 2024-07-24
 */
public interface AdminTableService extends IService<AdminTable> {
    AdminTable login(LoginUser loginUser);

    //验证验证码
    boolean checkVcode(String vcode);

    String getVcode();
}
