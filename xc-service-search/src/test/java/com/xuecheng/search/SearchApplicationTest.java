package com.xuecheng.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

/**
 * @author User
 * @date 2019/11/25
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApplicationTest {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    RestClient restClient;

    @Test
    public  void test() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course_2");
        //searchRequest.searchType("course2");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);

        SearchHits hits = searchResponse.getHits();
        //匹配的总记录数
        long totalHits =  hits.getTotalHits();
        System.out.println(totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit searchHit:hits1) {
            String id = searchHit.getId();
            System.out.println(id);
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            System.out.println(sourceAsMap.get("name"));
        }

    }

}
