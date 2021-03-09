package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:统一异常处理类
 * @projectName:tensquare_parent
 * @see:com.tensquare.base.controller
 * @author:MartinKing
 * @createTime:2021/3/8 15:15
 * @version:1.0
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
