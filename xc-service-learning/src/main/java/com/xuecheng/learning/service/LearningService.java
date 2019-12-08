package com.xuecheng.learning.service;

import com.xuecheng.framework.domain.course.TeachplanMediaPub;
import com.xuecheng.framework.domain.learning.XcLearningCourse;
import com.xuecheng.framework.domain.learning.request.LearningCourseRequest;
import com.xuecheng.framework.domain.learning.respones.GetMediaResult;
import com.xuecheng.framework.domain.learning.respones.LearningCode;
import com.xuecheng.framework.domain.media.MediaFile;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.*;
import com.xuecheng.learning.client.CourseSearchClient;
import com.xuecheng.learning.dao.LearingCourseJPA;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class LearningService {

    @Autowired
    CourseSearchClient courseSearchClient;

    @Autowired
    LearingCourseJPA learingCourseJPA;

    //获取课程学习地址（视频播放地址）
    public GetMediaResult getmedia(String courseId, String teachplanId) {
        //校验学生的学生权限...

        //远程调用搜索服务查询课程计划所对应的课程媒资信息
        TeachplanMediaPub teachplanMediaPub = courseSearchClient.getmedia(teachplanId);
        if(teachplanMediaPub == null || StringUtils.isEmpty(teachplanMediaPub.getMediaUrl())){
            //获取学习地址错误
            ExceptionCast.cast(LearningCode.LEARNING_GETMEDIA_ERROR);
        }
        return new GetMediaResult(CommonCode.SUCCESS,teachplanMediaPub.getMediaUrl());
    }

    public QueryResponseResult<XcLearningCourse> findXcCourseList (int page, int size, LearningCourseRequest learningCourseRequest){
        XcLearningCourse xcLearningCourse = new XcLearningCourse();
        if (learningCourseRequest!= null && learningCourseRequest.getUid()!=null) {
            xcLearningCourse.setUserId(learningCourseRequest.getUid());
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<XcLearningCourse> example = Example.of(xcLearningCourse,exampleMatcher);

        Pageable pageable = new PageRequest(page-1,size);
        Page<XcLearningCourse> all =  learingCourseJPA.findAll(example,pageable);

         //总记录数
        long total = all.getTotalElements();
        //数据列表
        List<XcLearningCourse> content = all.getContent();
        //返回的数据集
        QueryResult<XcLearningCourse> queryResult = new QueryResult<>();
        queryResult.setList(content);
        queryResult.setTotal(total);

        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    public ResponseResult addOpenCourse (XcLearningCourse xcLearningCourse) {
        if (xcLearningCourse != null) {
            XcLearningCourse xcLearningCourse1 = new XcLearningCourse();
            xcLearningCourse1.setCourseId(xcLearningCourse.getCourseId());
            xcLearningCourse1.setUserId(xcLearningCourse1.getId());
            Example<XcLearningCourse> example = Example.of(xcLearningCourse1);
            Optional<XcLearningCourse> one = learingCourseJPA.findOne(example);
            if (one.isPresent()) {
                return new ResponseResult(new ResultCode() {
                    @Override
                    public boolean success() {
                        return false;
                    }

                    @Override
                    public int code() {
                        return 1;
                    }

                    @Override
                    public String message() {
                        return "您已报名该课程";
                    }
                });
            }

            learingCourseJPA.save(xcLearningCourse);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
