package com.sx.userserver.billiardsTable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sx.userserver.billiardsTable.entity.Billiards;
import com.sx.userserver.billiardsTable.mapper.BilliardsMapper;
import com.sx.userserver.billiardsTable.service.BilliardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
//public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//
//}
public class BilliardsServiceImpl extends ServiceImpl<BilliardsMapper, Billiards> implements BilliardsService {

    @Autowired
    private BilliardsMapper billiardsMapper;

    // 查询id值对应台球桌的剩余时间
    @Override
    public String selectResidualById(Integer id) {
        LocalDateTime timestamp = billiardsMapper.selectResidualById(id);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = dateTimeFormatter.format(timestamp);

        return s;
    }

    // 更新台球桌信息
    @Override
    public int updateBilliards(Billiards billiards) {
        int ret=1;
        //判断是否有足够多的钱
        Float money = billiardsMapper.selectUserRemainMoney(billiards.getUserId());
        Integer hoursToAdd = billiards.getUsertime(); // 你想要增加的小时数
        float price = billiards.getPrice();
        if(money < (hoursToAdd*price)){ // 钱不够支付
            return 0;
        }



        // 表示当前时间大于数据库内存储的时间，说明时间到了，应该以现实时间增加小时数
        LocalDateTime newDateTime;
        if(billiards.getResidualTime().isBefore(LocalDateTime.now())) {
            newDateTime = LocalDateTime.now().plusHours(hoursToAdd);
        }else {
            newDateTime = billiards.getResidualTime().plusHours(hoursToAdd);
        }
        billiards.setResidualTime(newDateTime);
        Float f = money - hoursToAdd*price;
        billiardsMapper.updateUserMoney(f, Integer.valueOf(billiards.getUserId()));

        boolean b = billiardsMapper.updateBilliards(billiards);
        if(!b){// 失败
            ret=2;
        }

        // ret=1为成功，ret=0为钱不够，ret=2为钱足够但是更改时间出错
        return ret;
    }

    // 通过台球桌id查询当前id值对应的台球桌信息
    @Override
    public Billiards selectBilliardsById(Integer id) {
        return billiardsMapper.selectBilliardsById(id);
    }

    // 获取台球桌id值和到期时间并返回
    @Override
    public List<Billiards> getBilliardsId() {
        return billiardsMapper.getBilliardsId();
    }

    @Override
    public boolean settleAccounts(Integer id) {

        Billiards billiards = billiardsMapper.selectBilliardsById(id);
        Integer userId = billiards.getUserId();
        LocalDateTime now = LocalDateTime.now();

        // 计算两个时间之间的差异
        long daysBetween = ChronoUnit.DAYS.between(now, billiards.getResidualTime());
        long hoursBetween = ChronoUnit.HOURS.between(now, billiards.getResidualTime()) % 24; // 取余数得到小时数
        Integer money = (int) ((daysBetween*24 + hoursBetween) * billiards.getPrice());
        if(billiardsMapper.settleAccountsMoney(userId,money)){
            return billiardsMapper.settleAccountsTime(id,now);
        }

        return false;
    }
}