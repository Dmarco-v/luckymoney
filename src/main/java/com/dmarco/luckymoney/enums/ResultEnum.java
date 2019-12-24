package com.dmarco.luckymoney.enums;

public enum  ResultEnum {
    SUCCESS(0,"成功"),
    UNKNOWN_ERROR(-1,"未知错误"),
    BIG_MONEY(100,"感谢"),
    BIGGER_MONEY(101,"感谢土豪"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
