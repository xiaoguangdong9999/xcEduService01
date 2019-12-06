package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_course.dao.SysDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author User
 * @date 2019/11/24
 * @description
 */
@Service
public class SysDictionaryService {

    @Autowired
    SysDictionaryRepository sysDictionaryRepository;

    public SysDictionary findByType(String type) {
        return sysDictionaryRepository.findByDType(type);
    }
}
