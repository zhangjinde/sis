package com.huotu.sis.service.impl;

import com.huotu.huobanplus.common.entity.User;
import com.huotu.sis.entity.SisOpenAwardLog;
import com.huotu.sis.repository.SisOpenAwardLogRepository;
import com.huotu.sis.service.SisOpenAwardLogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by slt on 2016/2/19.
 */
@Service
public class SisOpenAwardLogServiceImpl implements SisOpenAwardLogService {
    private static Log log = LogFactory.getLog(SisOpenAwardLogServiceImpl.class);
    @Autowired
    SisOpenAwardLogRepository sisOpenAwardLogRepository;

    @Override
    public SisOpenAwardLog saveSisOpenAwardLog(Long customerId, User shop, User contribShop, Double amount,
                                               Integer srcType, String orderId) throws Exception {
        if(amount==null||amount<=0){
            log.info("user"+shop.getId()+"rebate is 0");
            return null;
        }
        SisOpenAwardLog sisOpenAwardLog=new SisOpenAwardLog();
        sisOpenAwardLog.setCustomerId(customerId);
        sisOpenAwardLog.setShopId(shop.getId());
        sisOpenAwardLog.setContribShopId(contribShop.getId());
        sisOpenAwardLog.setAmount(amount);
        String remark = srcType + "级会员(" + contribShop.getWxNickName() + ")贡献了开店奖";
        sisOpenAwardLog.setRemark(remark);
        sisOpenAwardLog.setAddTime(new Date());
        sisOpenAwardLog.setSrcType(srcType);
        sisOpenAwardLog.setOrderId(orderId);
        sisOpenAwardLog=sisOpenAwardLogRepository.save(sisOpenAwardLog);
        return sisOpenAwardLog;
    }
}
