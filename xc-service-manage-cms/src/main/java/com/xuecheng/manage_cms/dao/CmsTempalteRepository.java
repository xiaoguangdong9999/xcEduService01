package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsTempalteRepository extends MongoRepository<CmsTemplate, String> {
}
