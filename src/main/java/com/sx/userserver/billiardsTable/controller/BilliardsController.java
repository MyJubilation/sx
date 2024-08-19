package com.sx.userserver.billiardsTable.controller;


import com.sx.userserver.billiardsTable.entity.Billiards;
import com.sx.userserver.billiardsTable.service.BilliardsService;
import com.sx.userserver.common.result.Result;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/billiards")
public class BilliardsController {

    @Resource
    private BilliardsService billiardsService;



    @GetMapping("/date")
    public Result<?> getDate(Integer id){
        String dateTime = billiardsService.selectResidualById(id);

        return new Result<>().success().put(dateTime);
    }

    @PostMapping("/updateBilliards")
    public Result<?> updateBilliards(@RequestBody Billiards billiards){
        int ret = billiardsService.updateBilliards(billiards);
        // ret=1为成功，ret=0为钱不够，ret=2为钱足够但是更改时间出错
        if(ret == 1){
            return new Result<>().success();
        }else {
            if(ret == 0){
                return new Result<>().error("余额不足，请充值");
            }else {
                return new Result<>().error("更新时间失败");
            }
        }
    }

    @GetMapping("/selectBilliardsById")
    public Billiards selectBilliardsById(Integer id){
        return billiardsService.selectBilliardsById(id);

    }

    @GetMapping("/getBilliardsId")
    public Result<?> getBilliardsId(){
        List<Billiards> id = billiardsService.getBilliardsId();
        if(!ObjectUtils.isEmpty(id)){
            return new Result<>().success().put(id);
        }else {
            return new Result<>().error();
        }
    }

    @GetMapping("/settleAccounts")
    public Result<?> settleAccounts(Integer id){
        boolean b = billiardsService.settleAccounts(id);

        if(b){
            return new Result<>().success();
        }else {
            return new Result<>().error();
        }
    }
}
