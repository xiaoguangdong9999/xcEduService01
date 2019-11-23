package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author User
 * @date 2019/11/22
 * @description
 */
@Api(value = "cms配置管理接口", description = "cms配置管理接口，提供数据模型的增、删、改、查")
public interface CmsConfigControllerApi {

    @ApiOperation("根据id查询cms配置信息")
    CmsConfig getModel(String id);
}
