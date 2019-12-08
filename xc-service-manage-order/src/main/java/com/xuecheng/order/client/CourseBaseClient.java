package com.xuecheng.order.client;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.domain.cms.response.CmsPostPageResult;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.ext.CourseView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator.
 */
@FeignClient(value = "XC-SERVICE-MANAGE-COURSE") //指定远程调用的服务名
@RequestMapping("/course")
public interface CourseBaseClient {
    //查询课程详情
    @GetMapping("/coursebase/{course_id}")
    public CourseBase getCourseBaseById(@PathVariable("course_id") String courseId);


    @GetMapping(value = "/courseview/{id}", produces = {"application/json;charset=UTF-8"})
    public CourseView courseview(@PathVariable("id") String id);

}
