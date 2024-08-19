package com.sx.userserver.billiardsTable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sx.userserver.billiardsTable.entity.Billiards;

import java.util.List;


public interface BilliardsService extends IService<Billiards> {

    String selectResidualById(Integer id);

    int updateBilliards(Billiards billiards);

    Billiards selectBilliardsById(Integer id);

    List<Billiards> getBilliardsId();

    boolean settleAccounts(Integer id);


}
