package com.aiPeng.aiP.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ResultCode {
    /**
     * 返回码对应的值
     */
    SUCCESS("000000", "成功"),
    BIZ_EXCEPTION("000001", "异常"),
    ;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter
    private String code;
    @Getter
    private String msg;


}
