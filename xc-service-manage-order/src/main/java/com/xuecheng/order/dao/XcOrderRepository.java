package com.xuecheng.order.dao;

import com.xuecheng.framework.domain.order.XcOrders;
import com.xuecheng.framework.domain.task.XcTaskHis;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator.
 */
public interface XcOrderRepository extends JpaRepository<XcOrders,String> {

    List<XcOrders> findAllByEndTimeBeforeAndStatusEquals(Date date,String status);
}
