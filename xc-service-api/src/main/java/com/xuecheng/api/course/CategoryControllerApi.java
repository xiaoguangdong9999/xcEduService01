package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 */

@Api(value = "课程分类管理", tags = {"课程分类管理"})
public interface CategoryControllerApi {

    @ApiOperation("查询分类")
    public QueryResponseResult<CategoryNode> findList();
}
