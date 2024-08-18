package com.sx.userserver.userTable.mapper;

import com.sx.userserver.userTable.entity.UserTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Beetles
 * @since 2024-07-24
 */

public interface UserTableMapper extends BaseMapper<UserTable> {
    List<UserTable> selectByUserName();
}
