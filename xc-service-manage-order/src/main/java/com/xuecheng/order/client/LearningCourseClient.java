package com.xuecheng.order.client;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.learning.XcLearningCourse;
import com.xuecheng.framework.model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by Administrator.
 */
@FeignClient(value = "XC-SERVICE-MANAGE-LEARNING") //指定远程调用的服务名
@RequestMapping("/learning")
public interface LearningCourseClient {

    @PostMapping("/choosecourse/addopencourse/{courseId}")
    public ResponseResult addopencourse(@PathVariable("courseId") String courseId);




}
