package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.Category;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.dao.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author User
 * @date 2019/11/24
 * @description
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * 查询课程分类
     * @param parentid
     * @return
     */
    public QueryResponseResult<Category> findCategoryList(String parentid) {
        List<Category> categories= categoryRepository.findAllByParentid(parentid);
        QueryResult<Category> queryResult = new QueryResult<>();
        queryResult.setList(categories);
        queryResult.setTotal(categories.size());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);

    }
}
