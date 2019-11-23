package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @author User
 * @date 2019/11/22
 * @description
 */
public class ExceptionCast {
    public static void cast (ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
