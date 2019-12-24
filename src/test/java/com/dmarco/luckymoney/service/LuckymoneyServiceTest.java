package com.dmarco.luckymoney.service;

import com.dmarco.luckymoney.domain.Luckymoney;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
class LuckymoneyServiceTest {

    @Autowired
    private LuckymoneyService luckymoneyService;

    @Test
    void getLuckyMoneyById() {
        Luckymoney luckymoney=luckymoneyService.getLuckyMoneyById(2);
        Assert.assertEquals("a",luckymoney.getProducer());
    }
}