package com.sx.userserver.billiardsTable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sx.userserver.billiardsTable.entity.Billiards;
import com.sx.userserver.common.result.Result;
import com.sx.userserver.userTable.entity.UserTable;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sdx2009
 * @since 2024-07-15
 */
@Mapper
public interface BilliardsMapper extends BaseMapper<Billiards> {

    LocalDateTime selectResidualById(Integer id);

    Billiards selectBilliardsById(Integer id);

    boolean updateBilliards(Billiards billiards);

    List<Billiards> getBilliardsId();

    Float selectUserRemainMoney(Integer userId);

    boolean updateUserMoney(Float money, Integer userId);

    boolean settleAccountsMoney(Integer userId, Integer money);
    boolean settleAccountsTime(Integer id, LocalDateTime now);
}
