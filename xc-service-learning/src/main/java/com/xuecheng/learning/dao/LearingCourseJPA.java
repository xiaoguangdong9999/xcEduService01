package com.xuecheng.learning.dao;

import com.xuecheng.framework.domain.learning.XcLearningCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author User
 * @date 2019/12/5
 * @description
 */
public interface LearingCourseJPA extends JpaRepository<XcLearningCourse,String> {
}
