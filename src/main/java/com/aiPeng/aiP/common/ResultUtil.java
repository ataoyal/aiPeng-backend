package com.aiPeng.aiP.common;

public class ResultUtil<T> {

    private ResultUtil() {
    }

    public static <T> ResultInfo<T> getSuccessResult(T data) {
        ResultInfo<T> resultInfo = new ResultInfo<T>();
        resultInfo.setCode(ResultCode.SUCCESS.getCode());
        resultInfo.setData(data);
        return resultInfo;
    }

    public static <T> ResultInfo<T> getSuccessResult(String resultMessage, T data) {
        ResultInfo<T> resultInfo = new ResultInfo<T>();
        resultInfo.setCode(ResultCode.SUCCESS.getCode());
        resultInfo.setMessage(resultMessage);
        resultInfo.setData(data);
        return resultInfo;
    }

    public static <T> ResultInfo<T> getFailResult(T data) {
        ResultInfo<T> resultInfo = new ResultInfo<T>();
        resultInfo.setCode(ResultCode.BIZ_EXCEPTION.getCode());
        resultInfo.setMessage(ResultCode.BIZ_EXCEPTION.getMsg());
        resultInfo.setData(data);
        return resultInfo;
    }

}
