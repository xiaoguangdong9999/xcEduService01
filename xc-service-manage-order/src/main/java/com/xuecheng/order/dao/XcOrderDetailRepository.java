package com.xuecheng.order.dao;

import com.xuecheng.framework.domain.order.XcOrders;
import com.xuecheng.framework.domain.order.XcOrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator.
 */
public interface XcOrderDetailRepository extends JpaRepository<XcOrdersDetail,String> {
}
