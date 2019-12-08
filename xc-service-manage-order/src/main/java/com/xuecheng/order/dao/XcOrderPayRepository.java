package com.xuecheng.order.dao;

import com.xuecheng.framework.domain.order.XcOrdersDetail;
import com.xuecheng.framework.domain.order.XcOrdersPay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator.
 */
public interface XcOrderPayRepository extends JpaRepository<XcOrdersPay,String> {

    List<XcOrdersPay> findAllByOrderNumberIn(List<String> orders);
}
