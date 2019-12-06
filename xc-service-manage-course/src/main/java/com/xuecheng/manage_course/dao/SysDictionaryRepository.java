package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author User
 * @date 2019/11/24
 * @description
 */
public interface SysDictionaryRepository extends MongoRepository<SysDictionary,String> {
    public SysDictionary findByDType(String type);
}
