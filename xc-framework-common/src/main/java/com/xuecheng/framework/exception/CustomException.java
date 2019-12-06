package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @author User
 * @date 2019/11/22
 * @description
 */
public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException (ResultCode resultCode) {
        super("错误代码"+resultCode.code()+"错误信息"+resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }
}
