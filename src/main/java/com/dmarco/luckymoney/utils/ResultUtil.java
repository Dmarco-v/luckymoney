package com.dmarco.luckymoney.utils;

import com.dmarco.luckymoney.domain.CommonResult;

public class ResultUtil {

    public static CommonResult success(Object object){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(0);
        commonResult.setMsg("请求成功");
        commonResult.setData(object);
        return commonResult;
    }

    public static CommonResult success(){
        return success(null);
    }

    /**
     * 请求失败
     * @param code 1代表请求失败
     * @param msg
     * @return
     */
    public static CommonResult error(Integer code,String msg){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(code);
        commonResult.setMsg(msg);
        return commonResult;
    }

}
