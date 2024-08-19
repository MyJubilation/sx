package com.sx.userserver.billiardsTable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sx.userserver.billiardsTable.entity.Billiards;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;


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
