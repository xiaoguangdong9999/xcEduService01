package com.xuecheng.learning.controller;

import com.xuecheng.api.learning.CourseLearningControllerApi;
import com.xuecheng.framework.domain.learning.XcLearningCourse;
import com.xuecheng.framework.domain.learning.request.LearningCourseRequest;
import com.xuecheng.framework.domain.learning.respones.GetMediaResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.utils.XcOauth2Util;
import com.xuecheng.framework.web.BaseController;
import com.xuecheng.learning.service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/learning")
public class CourseLearningController extends BaseController implements CourseLearningControllerApi {

    @Autowired
    LearningService learningService;

    @Override
    @GetMapping("/course/getmedia/{courseId}/{teachplanId}")
    public GetMediaResult getmedia(@PathVariable("courseId") String courseId,
                                   @PathVariable("teachplanId")String teachplanId) {
        return learningService.getmedia(courseId,teachplanId);
    }

    @Override
    @PostMapping("/course/choosecourse/list/{page}/{size}")
    public QueryResponseResult<XcLearningCourse> getCourseList(@PathVariable("page") int page,
                                                               @PathVariable("size") int size,
                                                               @RequestBody LearningCourseRequest learningCourseRequest) {
        return learningService.findXcCourseList(page, size, learningCourseRequest);
    }

    @Override
    @PostMapping("/choosecourse/addopencourse/{courseId}")
    public ResponseResult addopencourse(@PathVariable("courseId") String courseId) {

        XcLearningCourse xcLearningCourse = new XcLearningCourse();
        xcLearningCourse.setId(String.valueOf(System.currentTimeMillis()));
        xcLearningCourse.setCourseId(courseId);
        xcLearningCourse.setUserId(getUserId());
        xcLearningCourse.setStartTime(new Date());
        xcLearningCourse.setValid("0");
        xcLearningCourse.setStatus("501001");
        return learningService.addOpenCourse(xcLearningCourse);
    }

    private String getUserId () {
        XcOauth2Util xcOauth2Util = new XcOauth2Util();
        XcOauth2Util.UserJwt userJwt = xcOauth2Util.getUserJwtFromHeader(request);
        //当前用户ID
        return userJwt.getId();
    }
}
