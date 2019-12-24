package com.dmarco.luckymoney.controller;

import com.dmarco.luckymoney.domain.CommonResult;
import com.dmarco.luckymoney.domain.Luckymoney;
import com.dmarco.luckymoney.repository.LuckymoneyRepository;
import com.dmarco.luckymoney.service.LuckymoneyService;
import com.dmarco.luckymoney.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckymoneyController {

    @Autowired
    private LuckymoneyRepository luckymoneyRepository;

    @Autowired
    private LuckymoneyService luckymoneyService;

    private static final Logger logger= LoggerFactory.getLogger(LuckymoneyController.class);

    /**
     * 获取红包列表
     */
    @GetMapping("/list")
    public List<Luckymoney> listLuckymoney(){
        return luckymoneyRepository.findAll();
    }

    /**
     * 通过id查询红包
     */
    @GetMapping("get")
    public Luckymoney getLuckymoneyById(@RequestParam("id") Integer id){
        logger.info("get id="+id);
        return luckymoneyService.getLuckyMoneyById(id);
    }


    /**
     * 创建红包
     */
    @PostMapping("/create")
    public CommonResult<Luckymoney> create(@Valid Luckymoney luckymoney, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        luckymoney.setProducer(luckymoney.getProducer());
        luckymoney.setMoney(luckymoney.getMoney());
        return ResultUtil.success(luckymoneyRepository.save(luckymoney));
    }

    /**
     * 事务测试
     */
    @PostMapping("/createtwo")
    public void createtwo(@RequestParam("producer") String producer,@RequestParam("money") BigDecimal money){
        Luckymoney luckymoney=new Luckymoney();
        luckymoney.setProducer(producer);
        luckymoney.setMoney(money);
        Luckymoney anotherLuckymoney=new Luckymoney();
        anotherLuckymoney.setProducer(producer);
        anotherLuckymoney.setMoney(money.multiply(new BigDecimal(1000)));
        luckymoneyService.createTwo(luckymoney,anotherLuckymoney);
    }

    /**
     * 更新红包
     */
    @PutMapping("/update/{id}")
    public Luckymoney update(@PathVariable("id")Integer id,@RequestParam("consumer") String consumer){
        Optional<Luckymoney> optional=luckymoneyRepository.findById(id);
        if(optional.isPresent()){
            Luckymoney luckymoney=optional.get();
            luckymoney.setConsumer(consumer);
            return luckymoneyRepository.save(luckymoney);
        }
        return null;
    }

    /**
     * 获取金额
     */
    @GetMapping("/getmoney")
    public void getMoney(@RequestParam("id") Integer id) throws Exception {
        Luckymoney luckymoney= luckymoneyService.getMoney(id);
        logger.info(luckymoney.toString());
    }

}
