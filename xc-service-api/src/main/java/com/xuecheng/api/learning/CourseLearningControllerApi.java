package com.xuecheng.api.learning;

import com.xuecheng.framework.domain.course.CoursePub;
import com.xuecheng.framework.domain.learning.XcLearningCourse;
import com.xuecheng.framework.domain.learning.request.LearningCourseRequest;
import com.xuecheng.framework.domain.learning.respones.GetMediaResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

/**
 * Created by Administrator.
 */
@Api(value = "录播课程学习管理",tags = "录播课程学习管理")
public interface CourseLearningControllerApi {

    @ApiOperation("获取课程学习地址")
    public GetMediaResult getmedia(String courseId, String teachplanId);

    @ApiOperation("获取课程列表")
    public QueryResponseResult<XcLearningCourse> getCourseList(int page, int size, LearningCourseRequest learningCourseRequest);


    @ApiOperation("报名课程")
    public ResponseResult addopencourse(String courseId);

}
