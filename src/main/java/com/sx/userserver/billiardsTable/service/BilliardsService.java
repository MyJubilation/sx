package com.sx.userserver.billiardsTable.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sx.userserver.billiardsTable.entity.Billiards;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sdx2009
 * @since 2024-07-15
 */
public interface BilliardsService extends IService<Billiards> {

    String selectResidualById(Integer id);

    int updateBilliards(Billiards billiards);

    Billiards selectBilliardsById(Integer id);

    List<Billiards> getBilliardsId();

    boolean settleAccounts(Integer id);


}
