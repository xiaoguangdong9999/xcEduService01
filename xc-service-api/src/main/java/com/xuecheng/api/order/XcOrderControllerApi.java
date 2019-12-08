package com.xuecheng.api.order;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.order.XcOrders;
import com.xuecheng.framework.domain.order.request.CreateOrderRequest;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

/**
 * @author User
 * @date 2019/12/7
 * @description
 */
@Api(value = "订单管理", description = "订单管理")
public interface XcOrderControllerApi {
    @ApiOperation("创建订单")
    ResponseResult createOrder(CreateOrderRequest createOrderRequest);

    @ApiOperation("订单列表")
    ResponseResult list(int page,int size,XcOrders xcOrders);

    @ApiOperation("根据ID查询订单")
    ResponseResult getOrderById(String orderNum);



}
