package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.CoursePublishResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.client.CmsPageClient;
import com.xuecheng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {

    @Autowired
    CourseService courseService;

    @Autowired
    CmsPageClient cmaPageClient;

    /**
     * 查询课程计划
     * @param courseId
     * @return
     */
    @Override
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachplanList(courseId);

    }

    /**
     * 添加课程计划
     * @param teachplan
     * @return
     */
    @Override
    @PostMapping("/teachplan/add")
    public ResponseResult addTeachplan(@RequestBody  Teachplan teachplan) {
        return courseService.addTeachplan(teachplan);
    }

    /**
     * 查询课程信息列表
     * @param page
     * @param size
     * @param courseListRequest
     * @return
     */
    @Override
    @PostMapping("/courseinfo/list/{page}/{size}")
    public QueryResponseResult<CourseInfo> findCourseList(@PathVariable("page") int page,@PathVariable("size") int size, @RequestBody CourseListRequest courseListRequest) {
        return courseService.findCourseList(page,size,courseListRequest);
    }

    /**
     * 添加课程
     * @param courseBase
     * @return
     */
    @Override
    @PostMapping("/coursebase/add")
    public ResponseResult addCourseBase(@RequestBody CourseBase courseBase) {
        return courseService.add(courseBase);
    }

    /**
     * 获取课程
     * @param courseId
     * @return
     */
    @Override
    @GetMapping("/coursebase/{course_id}")
    public CourseBase getCourseBaseById(@PathVariable("course_id") String courseId) {
        return courseService.getCourseBase(courseId);
    }

    /**
     * 编辑课程
     * @param courseId
     * @param courseBase
     * @return
     */
    @Override
    @PostMapping("/coursebase/update/{course_id}")
    public ResponseResult updateCourseBase(@PathVariable("course_id") String courseId, @RequestBody CourseBase courseBase) {
        return courseService.updateCourse(courseId,courseBase);
    }

    /**
     * 获取课程营销信息
     * @param courseMarketId
     * @return
     */
    @Override
    @GetMapping("/coursemarket/{course_id}")
    public CourseMarket getCourseMarketById(@PathVariable("course_id") String courseMarketId) {
        return courseService.getCourseMarketById(courseMarketId);
    }

    /**
     * 更新课程营销信息
     * @param courseMarketId
     * @param courseMarket
     * @return
     */
    @Override
    @PostMapping("/coursemarket/update/{coursemarket_id}")
    public ResponseResult updateCourseMarket(@PathVariable("coursemarket_id") String courseMarketId, CourseMarket courseMarket) {
        return courseService.updateCourseMarket(courseMarketId,courseMarket);
    }

    /**
     * 课程视图查询
     * @param id
     * @return
     */
    @Override
    @GetMapping("/courseview/{id}")
    public CourseView courseview(@PathVariable("id") String id) {
        return courseService.getCourseView(id);
    }

    /**
     * 课程视图预览
     * @param id
     * @return
     */
    @Override
    @GetMapping("/preview/{id}")
    public CoursePublishResult preview(String id) {
        return courseService.preview(id);
    }
}
