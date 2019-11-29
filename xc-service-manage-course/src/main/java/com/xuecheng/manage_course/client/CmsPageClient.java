package com.xuecheng.manage_course.client;

import com.xuecheng.framework.client.XcServiceList;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.xuecheng.framework.client.XcServiceList.XC_SERVICE_MANAGE_CMS;

/**
 * @author User
 * @date 2019/11/25
 * @description
 */
@FeignClient(value = XC_SERVICE_MANAGE_CMS)
public interface CmsPageClient {

    @GetMapping("/cms/page/get/{id}")
    public CmsPageResult findById(@PathVariable("id") String id);

    @PostMapping("/cms/page/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage);
}
