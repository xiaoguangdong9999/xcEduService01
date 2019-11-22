package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:32
 **/
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;


    /**
     * 页面查询方法
     *
     * @param page             页码，从1开始记数
     * @param size             每页记录数
     * @param queryPageRequest 查询条件
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNotEmpty(cmsPage.getSiteId())) {
            cmsPage.setSiteId(cmsPage.getPageId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }

        Example example = Example.of(cmsPage,exampleMatcher);
        //分页参数
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        Page<CmsPage> all = cmsPageRepository.findAll(example,PageRequest.of(page,size));
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    /**
     * 校验页面是否存在
     * @param cmsPage
     * @return
     */
    public CmsPageResult add (CmsPage cmsPage) {
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
        if (cmsPage1 == null) {
            cmsPage.setPageId(null);
            cmsPageRepository.save(cmsPage);
            CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS,cmsPage);
            return cmsPageResult;
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    /**
     * 根据ID查询页面
     * @param id
     * @return
     */
    public CmsPageResult findById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS,optional.get());
            return cmsPageResult;
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    public CmsPageResult update (String id ,CmsPage cmsPage) {
        CmsPageResult cmsPageResult1 = this.findById(id);

        if (cmsPageResult1.isSuccess()) {
            CmsPage one =  cmsPageResult1.getCmsPage();
            one.setPageId(cmsPage.getPageId());
            one.setPageAliase(cmsPage.getPageAliase());
            one.setSiteId(cmsPage.getSiteId());
            one.setDataUrl(cmsPage.getDataUrl());
            one.setPageName(cmsPage.getPageName());
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            one.setPageWebPath(cmsPage.getPageWebPath());
            CmsPage save =  cmsPageRepository.save(one);
            if (save != null) {
                CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS,save);
                return cmsPageResult;
            }
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }


    public ResponseResult delete(String id) {
        CmsPageResult cmsPageResult =  this.findById(id);
        if (cmsPageResult.isSuccess()) {
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
