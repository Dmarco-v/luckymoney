package com.dmarco.luckymoney.handle;

import com.dmarco.luckymoney.domain.CommonResult;
import com.dmarco.luckymoney.exception.MoneyException;
import com.dmarco.luckymoney.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public CommonResult handle(Exception e){
        if(e instanceof  MoneyException){
            MoneyException moneyException= (MoneyException) e;
            return ResultUtil.error(moneyException.getCode(),moneyException.getMessage());
        }else{
            logger.error("【系统异常】：",e);
            return ResultUtil.error(-1,"未知错误");
        }


    }

}
