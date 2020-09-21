package learning.hui.demo.springboot.es.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Hui Lyu
 *
 * CREATE TABLE `blog` (
 *   `id` int NOT NULL COMMENT 'auto_increment ID',
 *   `title` varchar(60) DEFAULT NULL COMMENT 'blog title',
 *   `author` varchar(60) DEFAULT NULL COMMENT 'blog author',
 *   `content` mediumtext COMMENT 'blog content',
 *   `create_time` datetime DEFAULT NULL COMMENT 'create time',
 *   `update_time` datetime DEFAULT NULL COMMENT 'update time',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 *
 */
@Data
@Table(name = "blog")
@Entity
public class MysqlBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    @Column(columnDefinition = "mediumtext")
    private String content;
    private Date createTime;
    private Date updateTime;

}
