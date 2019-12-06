package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.service.PageService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:11
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ManageCmsApplicationTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    GridFsTemplate fsTemplate;

    @Autowired
    PageService pageService;

    @Test
    public  void testPage() {
        try {
            String pageHtml = pageService.getPageHtml("5a795ac7dd573c04508f3a56");
            System.out.println(pageHtml);
        }catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.println(a);
    }

    @Test
    public void fsTest () throws Exception{
        File file = new File("C:/Users/User/Desktop/course.ftl");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId store = fsTemplate.store(fileInputStream, "测试文件课程详情","");
        System.out.println(store);
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.println(a);
    }

    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);

    }

    //分页查询
    @Test
    public void testFindPage(){
        //分页参数
        int page = 1;//从0开始
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }

    //修改
    @Test
    public void testUpdate() {
        //查询对象
        Optional<CmsPage> optional = cmsPageRepository.findById("5b4b1d8bf73c6623b03f8cec");
        if(optional.isPresent()){
            CmsPage cmsPage = optional.get();
            //设置要修改值
            cmsPage.setPageAliase("test01");
            //...
            //修改
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);
        }

    }

    //根据页面名称查询
    @Test
    public void testfindByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("测试页面");
        System.out.println(cmsPage);
    }
}
