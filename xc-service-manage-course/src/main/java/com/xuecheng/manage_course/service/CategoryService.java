package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.Category;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.dao.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * @return
     */
    public QueryResponseResult<CategoryNode> findCategoryList() {
        List<Category> categoriesList = categoryRepository.findAllByParentid("1");
        List<CategoryNode> categoryNodes = new ArrayList<>();
        for (Category c :categoriesList) {
            CategoryNode categoryNode = new CategoryNode();
            categoryNode.setId(c.getId());
            categoryNode.setIsleaf(c.getIsleaf());
            categoryNode.setIsshow(c.getIsshow());
            categoryNode.setLabel(c.getLabel());
            categoryNode.setName(c.getName());
            categoryNode.setOrderby(c.getOrderby());
            categoryNode.setParentid(c.getParentid());
            List<Category> children = categoryRepository.findAllByParentid(c.getId());
            List<CategoryNode> categoryNodes1 = new ArrayList<>();
            for (Category ch :children) {
                CategoryNode categoryNode1 = new CategoryNode();
                categoryNode1.setId(ch.getId());
                categoryNode1.setIsleaf(ch.getIsleaf());
                categoryNode1.setIsshow(ch.getIsshow());
                categoryNode1.setLabel(ch.getLabel());
                categoryNode1.setName(ch.getName());
                categoryNode1.setOrderby(ch.getOrderby());
                categoryNode1.setParentid(ch.getParentid());
                categoryNodes1.add(categoryNode1);
            }
            categoryNode.setChildren(categoryNodes1);
            categoryNodes.add(categoryNode);
        }
        QueryResult<CategoryNode> queryResult = new QueryResult<>();
        queryResult.setList(categoryNodes);
        queryResult.setTotal(categoryNodes.size());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);

    }
}
