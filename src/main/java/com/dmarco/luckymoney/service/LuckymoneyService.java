package com.dmarco.luckymoney.service;

import com.dmarco.luckymoney.domain.Luckymoney;
import com.dmarco.luckymoney.enums.ResultEnum;
import com.dmarco.luckymoney.exception.MoneyException;
import com.dmarco.luckymoney.repository.LuckymoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class LuckymoneyService {

    @Autowired
    private LuckymoneyRepository luckymoneyRepository;

    /**
     * 事务
     */
    @Transactional
    public void createTwo(Luckymoney luckymoney, Luckymoney anotherLuckymoney){
        luckymoneyRepository.save(luckymoney);
        luckymoneyRepository.save(anotherLuckymoney);
    }

    public Luckymoney getMoney(Integer id) throws Exception {
        Luckymoney luckymoney=luckymoneyRepository.getOne(id);
        BigDecimal money=luckymoney.getMoney();
        if(money.compareTo(new BigDecimal(100) )>=0){
            throw  new MoneyException(ResultEnum.BIG_MONEY);
        }else if(money.compareTo(new BigDecimal(500))>=0){
            throw  new MoneyException(ResultEnum.BIGGER_MONEY);
        }
        return luckymoney;
    }

    public Luckymoney getLuckyMoneyById(Integer id){
        return luckymoneyRepository.getOne(id);
    }
}
