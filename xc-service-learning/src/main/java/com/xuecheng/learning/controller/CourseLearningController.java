package com.xuecheng.learning.controller;

import com.xuecheng.api.learning.CourseLearningControllerApi;
import com.xuecheng.framework.domain.learning.XcLearningCourse;
import com.xuecheng.framework.domain.learning.request.LearningCourseRequest;
import com.xuecheng.framework.domain.learning.respones.GetMediaResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.learning.service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/learning/course")
public class CourseLearningController implements CourseLearningControllerApi {

    @Autowired
    LearningService learningService;

    @Override
    @GetMapping("/getmedia/{courseId}/{teachplanId}")
    public GetMediaResult getmedia(@PathVariable("courseId") String courseId,
                                   @PathVariable("teachplanId")String teachplanId) {
        return learningService.getmedia(courseId,teachplanId);
    }

    @Override
    @PostMapping("/choosecourse/list/{page}/{size}")
    public QueryResponseResult<XcLearningCourse> getCourseList(@PathVariable("page") int page,
                                                               @PathVariable("size") int size,
                                                               @RequestBody LearningCourseRequest learningCourseRequest) {
        return learningService.findXcCourseList(page, size, learningCourseRequest);
    }
}
