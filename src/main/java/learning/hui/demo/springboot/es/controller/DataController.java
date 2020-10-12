package learning.hui.demo.springboot.es.controller;


import learning.hui.demo.springboot.es.entity.es.EsBlog;
import learning.hui.demo.springboot.es.entity.mysql.MysqlBlog;
import learning.hui.demo.springboot.es.repository.es.EsBlogRepository;
import learning.hui.demo.springboot.es.repository.mysql.MysqlBlogRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// because we use rest API, so this class will return a json format data, and add a restController
@RestController
public class DataController {

    @Autowired
    MysqlBlogRepository mysqlBlogRepository;

    @Autowired
    EsBlogRepository esBlogRepository;

    // spring mvc will return the value as a json format
    @GetMapping("/blogs")
    public Object blogs(){
        List<MysqlBlog> mysqlBlogList = mysqlBlogRepository.queryAll();
        return mysqlBlogList;
    }

    @PostMapping("/search")
    public Object search(Param param){
        HashMap<String, Object> map = new HashMap<>();
        StopWatch watch = new StopWatch();
        watch.start();
        String type = param.getType();
        if(type.equalsIgnoreCase("mysql")){
            //mysql
            List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryBlogs(param.getKeyword());
            map.put("list", mysqlBlogs);
        }else if(type.equalsIgnoreCase("es")){
            //es
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title", param.getKeyword()));
            builder.should(QueryBuilders.matchPhraseQuery("content", param.getKeyword()));
            String s = builder.toString();
            System.out.println(s);
            Page<EsBlog> search = (Page<EsBlog>) esBlogRepository.search(builder);
            List<EsBlog> content = search.getContent();
            map.put("list", content);
        }else {
            return "I don't know what you want to do";
        }

        watch.stop();
        long totalTimeMillis =  watch.getTotalTimeMillis();
        map.put("duration", totalTimeMillis);
        return map;
    }
    @GetMapping("/blog/{id}")
    public Object blog(@PathVariable("id") Integer id){
        Optional<MysqlBlog> byId = mysqlBlogRepository.findById(id);
        MysqlBlog blog = byId.get();
        return blog;
    }

    @Data
    public static class Param{
        // from the front-end will received a param is mysql, or es
        private String type;
        private String keyword;
    }
}
