package learning.hui.demo.springboot.es.entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author Hui Lyu
 */

@Data
@Document(indexName = "blog", useServerConfiguration = true,createIndex = false)
public class EsBlog {
    @Id
    private Integer id;

    @Field(type = FieldType.Text, analyzer = "Stardard")
    private String title;
    @Field(type = FieldType.Text, analyzer = "Stardard")
    private String author;
    @Field(type = FieldType.Text, analyzer = "Stardard")
    private String content;
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || epoch_millis" )
    private Date createTime;
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || epoch_millis" )
    private Date updateTime;

}
