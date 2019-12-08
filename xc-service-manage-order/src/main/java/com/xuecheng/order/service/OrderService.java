package com.xuecheng.order.service;

import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.learning.XcLearningCourse;
import com.xuecheng.framework.domain.order.XcOrders;
import com.xuecheng.framework.domain.order.XcOrdersDetail;
import com.xuecheng.framework.domain.order.XcOrdersPay;
import com.xuecheng.framework.model.response.*;
import com.xuecheng.order.dao.XcOrderDetailRepository;
import com.xuecheng.order.dao.XcOrderPayRepository;
import com.xuecheng.order.dao.XcOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author User
 * @date 2019/12/7
 * @description
 */
@Service
public class OrderService {

    @Autowired
    XcOrderRepository xcOrderRepository;

    @Autowired
    XcOrderDetailRepository xcOrderDetailRepository;

    @Autowired
    XcOrderPayRepository xcOrderPayRepository;

    public ResponseResult saveOrder (XcOrders xcOrders) {

        XcOrders xcOrders1 = new XcOrders();
        xcOrders1.setStatus("401001");
        Example<XcOrders> xcOrdersExample = Example.of(xcOrders1);
        List<XcOrders> all = xcOrderRepository.findAll(xcOrdersExample);
        if (!CollectionUtils.isEmpty(all)) {
            return new ResponseResult(new ResultCode() {
                @Override
                public boolean success() {
                    return false;
                }

                @Override
                public int code() {
                    return 1;
                }

                @Override
                public String message() {
                    return "您有为完成的订单，请完成后进行创建订单";
                }
            });
        }
        xcOrderRepository.save(xcOrders);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public ResponseResult batchSaveXcOrderDetail (List<XcOrdersDetail> xcOrdersDetails) {
        List<XcOrdersDetail> xcOrdersDetails1 = xcOrderDetailRepository.saveAll(xcOrdersDetails);
        if (xcOrdersDetails1.size()== xcOrdersDetails.size()) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    public QueryResponseResult<XcOrders> list (int page,int size, XcOrders xcOrders) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<XcOrders> example = Example.of(xcOrders,exampleMatcher);

        Pageable pageable = new PageRequest(page-1,size);
        Page<XcOrders> all = xcOrderRepository.findAll(example,pageable);
        QueryResult<XcOrders> queryResult = new QueryResult<>();

        List<XcOrders> xcOrdersList = new ArrayList<>();
        for (XcOrders x : all.getContent()) {
            XcOrdersDetail xcOrdersDetail = new XcOrdersDetail();
            xcOrdersDetail.setOrderNumber(x.getOrderNumber());
            Example<XcOrdersDetail> xcOrdersDetailExample = Example.of(xcOrdersDetail);
            List<XcOrdersDetail> xcOrdersDetails = xcOrderDetailRepository.findAll(xcOrdersDetailExample);
            x.setXcOrdersDetails(xcOrdersDetails);
            xcOrdersList.add(x);
        }

        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(xcOrdersList);
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    public ResponseResult<XcOrders> get(String orderNum) {
        Optional<XcOrders> optional = xcOrderRepository.findById(orderNum);
        return optional.map(xcOrders -> new ResponseResult<>("操作成功", xcOrders)).orElseGet(() -> new ResponseResult<>(CommonCode.FAIL));
    }

    @Transactional
    public boolean updateOrderPayStatus (String orderNum,String payNum) {
        XcOrdersPay xcOrdersPay = new XcOrdersPay();
        xcOrdersPay.setOrderNumber(orderNum);
        Example<XcOrdersPay> example = Example.of(xcOrdersPay);
        Optional<XcOrdersPay> one = xcOrderPayRepository.findOne(example);

        XcOrders xcOrders = new XcOrders();
        xcOrders.setOrderNumber(orderNum);
        Example<XcOrders> example1 = Example.of(xcOrders);
        Optional<XcOrders> one1 = xcOrderRepository.findOne(example1);
        if (one1.isPresent()) {
            XcOrders xcOrders1 = one1.get();
            xcOrders1.setStatus("401002");
            xcOrderRepository.save(xcOrders1);
        }
        if (one.isPresent()) {
            XcOrdersPay xcOrdersPay1 = one.get();
            xcOrdersPay1.setPayNumber(payNum);
            xcOrdersPay1.setStatus("402002");
            xcOrderPayRepository.save(xcOrdersPay1);
        }
        return true;
    }
}
