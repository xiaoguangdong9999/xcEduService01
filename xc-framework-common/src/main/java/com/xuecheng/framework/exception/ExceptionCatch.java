package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.netflix.hystrix.util.Exceptions;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author User
 * @date 2019/11/22
 * @description
 */
@ControllerAdvice
public class ExceptionCatch {

    private static final Logger log = (Logger) LoggerFactory.getLogger(ExceptionCatch.class);

    private static ImmutableMap<Class<? extends  Throwable>,ResultCode> EXCEPTIONS;
    protected static ImmutableMap.Builder<Class<? extends  Throwable>,ResultCode> builder = ImmutableMap.builder();

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException (CustomException e) {
        log.error("catch exception:{}exception",e.getMessage(),e);
        ResultCode resultCode = e.getResultCode();
        ResponseResult responseResult = new ResponseResult(resultCode);
        return responseResult;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception (Exception e) {
        log.error("catch exception:{}exception",e.getMessage());
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        final ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        final ResponseResult responseResult;
        if (resultCode != null) {
            responseResult = new ResponseResult(resultCode);
        }else {
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return responseResult;
    }

}
