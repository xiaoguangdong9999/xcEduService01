package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author User
 * @date 2019/11/24
 * @description
 */

public interface CategoryRepository extends JpaRepository<Category,String> {

    List<Category> findAllByParentid(String parent);

}
