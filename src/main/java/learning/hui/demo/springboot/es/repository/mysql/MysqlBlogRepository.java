package learning.hui.demo.springboot.es.repository.mysql;

import learning.hui.demo.springboot.es.entity.mysql.MysqlBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlBlogRepository extends JpaRepository<MysqlBlog, Integer> {
}
