package com.xuecheng.manage_course;

import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.manage_course.client.CmsPageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:11
 **/
@SpringBootTest(classes = ManageCourseApplicationTest.class)
@RunWith(SpringRunner.class)
public class ManageCourseApplicationTest {

    @Autowired
    CmsPageClient cmaPageClient;
    /*@Test
    public  void testPage() {
       CmsPageResult cmsPageResult = cmaPageClient.findById("sfsdfsdfsdf");
        System.out.println(cmsPageResult.getCmsPage().toString());
    }
*/


}
