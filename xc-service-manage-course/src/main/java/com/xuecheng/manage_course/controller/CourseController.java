package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.*;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.CoursePublishResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.utils.XcOauth2Util;
import com.xuecheng.framework.web.BaseController;
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
public class CourseController extends BaseController implements CourseControllerApi {

    @Autowired
    CourseService courseService;

    @Autowired
    CmsPageClient cmaPageClient;

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
    public ResponseResult updateCourseMarket(@PathVariable("coursemarket_id") String courseMarketId, @RequestBody  CourseMarket courseMarket) {
        return courseService.updateCourseMarket(courseMarketId,courseMarket);
    }

    //当用户拥有course_teachplan_list权限时候方可访问此方法
    //@PreAuthorize("hasAuthority('course_teachplan_list')")
    @Override
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachplanList(courseId);
    }

    //@PreAuthorize("hasAuthority('course_teachplan_add')")
    @Override
    @PostMapping("/teachplan/add")
    public ResponseResult addTeachplan(@RequestBody  Teachplan teachplan) {

        return courseService.addTeachplan(teachplan);
    }

    @Override
    @PostMapping("/coursepic/add")
    public ResponseResult addCoursePic(@RequestParam("courseId") String courseId, @RequestParam("pic")String pic) {
        return courseService.addCoursePic(courseId,pic);
    }

    //当用户拥有course_pic_list权限时候方可访问此方法
    //@PreAuthorize("hasAuthority('course_pic_list')")
    @Override
    @GetMapping("/coursepic/list/{courseId}")
    public CoursePic findCoursePic(@PathVariable("courseId") String courseId) {
        return courseService.findCoursePic(courseId);
    }

    @Override
    @DeleteMapping("/coursepic/delete")
    public ResponseResult deleteCoursePic(@RequestParam("courseId") String courseId) {
        return courseService.deleteCoursePic(courseId);
    }

    @Override
    @GetMapping("/courseview/{id}")
    public CourseView courseview(@PathVariable("id") String id) {
        return courseService.getCoruseView(id);
    }

    @Override
    @PostMapping("/preview/{id}")
    public CoursePublishResult preview(@PathVariable("id") String id) {
        return courseService.preview(id);

    }

    @Override
    @PostMapping("/publish/{id}")
    public CoursePublishResult publish(@PathVariable("id")String id) {
        return courseService.publish(id);
    }

    @Override
    @PostMapping("/savemedia")
    public ResponseResult savemedia(@RequestBody TeachplanMedia teachplanMedia) {
        return courseService.savemedia(teachplanMedia);
    }

    @Override
    @PostMapping("/courseinfo/list/{page}/{size}")
    public QueryResponseResult<CourseInfo> findCourseList(@PathVariable("page") int page,
                                                          @PathVariable("size") int size,
                                                          CourseListRequest courseListRequest) {
        /*//获取当前用户信息
        XcOauth2Util xcOauth2Util = new XcOauth2Util();
        XcOauth2Util.UserJwt userJwt = xcOauth2Util.getUserJwtFromHeader(request);*/
        //当前用户所属单位的id
        //String company_id = userJwt.getCompanyId();
        String company_id = "1";

        return courseService.findCourseList(company_id,page,size,courseListRequest);
    }
}
